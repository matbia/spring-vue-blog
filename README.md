# Spring Vue Blog
Example single-page application made using Spring Boot and Vue.js integration.

## Features
 - REST API implementing CRUD (create, read, update, delete) functionality
 - Basic authentication
 - Markdown formatting

## Usage
Open the *application.properties* file and edit it accordingly by providing a correct database address and credentials.
Using a database engine different than PostgreSQL requires changing the JDBC driver. 
Once the proper configuration is in place, just compile and run. By default Tomcat will start on port 8080.

Can also be packaged into a JAR using Maven:
```
mvn clean package
```
And run:
```
java -jar webapp-0.0.1-SNAPSHOT.jar
```
Default username is **user**
Password is generated each time the application is ran and can be found in the console logs.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
