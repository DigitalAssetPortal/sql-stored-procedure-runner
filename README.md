# SQL Store procedure runner

### Files and Structure

- The SqlStoredProcedureRunner.java file goes in the src/ directory. 

- The JDBC driver JAR file (for eg: mysql-connector-java-8.0.26.jar [which also needs to be updated in manifest.txt]) goes in the lib/ directory.

- The config/ directory contains the config.properties file that specifies the database connection and stored procedure to execute.

- Finally, the manifest.txt file specifies the main class to use when running the JAR file.

### To build and run the JAR file, follow these steps:

- Compile the Java source file into .class files:

```bash
$ javac -d . src/SqlStoredProcedureRunner.java
```

- Create a JAR file containing the compiled classes, the config.properties file, and the manifest.txt file:

```bash
$ jar cvfm sql-stored-procedure-runner.jar manifest.txt -C . .
```

- Run the JAR file:

```bash
$ java -jar sql-stored-procedure-runner.jar
```

NOTE: Make sure that you are in the root of the project directory when running these commands.