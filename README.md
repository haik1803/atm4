# ATM4

## How To Run
- To run this project you need to install Maven and Java environment.
- Then run this project with following comand "mvn clean spring-boot:run" in the app directory.
- After executing the above command, iniate the data by uploading data.csv in base directory.
- To run this project with database connectivity you should setting up the connection in app\src\main\resources\application-prod.properties and then execute command "mvn clean spring-boot run -Pprod" for running it.
- In this project contain two profiles, dev and prod. 
- dev profile uses h2 database (in-memory) and when executing maven command such as 'mvn test' or 'mvn package' that require executing test script it will executing it.
- prod profile uses MySQL database and unlike dev profile it won't executing any test script.

## Purpose
- This project have purposes for learning spring boot with others function described in https://github.com/Mitrais/java-bootcamp-working/wiki at Study Case #4