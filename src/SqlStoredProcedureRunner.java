import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SqlStoredProcedureRunner {
    
    public static void main(String[] args) {
        String propertiesFile = "config.properties";
        
        try {
            // Load the configuration from the properties file
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(propertiesFile);
            props.load(fis);
            fis.close();
            
            // Load the JDBC driver
            String jdbcDriverClass = props.getProperty("jdbcDriverClass");
            Class.forName(jdbcDriverClass);
            
            // Set up the JDBC connection to the SQL server
            String jdbcUrl = props.getProperty("jdbcUrl");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            
            // Execute the stored procedure
            String storedProcName = props.getProperty("storedProcName");
            CallableStatement cstmt = conn.prepareCall("{ call " + storedProcName + " }");
            boolean hasResultSet = cstmt.execute();
            
            // Write the output to a file
            String outputFile = props.getProperty("outputFile");
            if (hasResultSet) {
                ResultSet rs = cstmt.getResultSet();
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                while (rs.next()) {
                    writer.write(rs.getString(1));
                    writer.newLine();
                }
                writer.close();
                rs.close();
            }

            // Clean up and exit
            cstmt.close();
            conn.close();
            System.exit(0);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}