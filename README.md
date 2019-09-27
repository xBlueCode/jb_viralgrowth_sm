
# jb_viralgrowth_sm



## Structure
### Configuration
All properties of the web application are centralized in the properties file and are mapped by properties classes under the package `xbc.jb.socialvg.refinv.properties`:

```
src/main/resources/application.yml
```

#### Database
For simplicity I'm using **H2 Database Engine** for *in-memory* database, it can be simply migrated to alternative servers like `MySQL` By specifying the appropriate `url` in the properties file.

```YAMEL
spring:  
  datasource:  
    url: jdbc:mysql://localhost:3306/db_name
  username: db_username 
  password: db_password
```
```
hibernate:  
  ddl-auto: update # create  
database: mysql
```
#### Properties
I made some properties of the web application in the property file to decouple them from the code and to make the application easily customizable.
```
webapp:
pagination:
page-size: 4
path:
file-sep: /
image-folder: /Users/abbesbes/images/
profile-def-img: /Users/abbesbes/images/default_profile.png
```
Description:
 - **webapp**: The root of the custom properties.
	 - **pagination**:Properties of DB pagination.
		 - **page-size**: The size of the page to be shown in the list / DB Page size.
	- **path**: Properties related to paths.
		- **file-sep**: File Seperator which is used for path building.
		- **image-folder**: The path of the folder wich contain the photos uploaded by users.
		- **profile-def-img**: Path to the default photo to be used as profile picture for users.

### Usage
The *Spring Boot Maven plugin* collects all jars on the classpath and build a single executable `.jar` which will be running on the server.

So all we need is to build this `.jar` and execute it on the server.

#### Using IntelliJ IDEA
After cloning the repository simply **import** the project into IntelliJ IDEA as **Maven Project**  by selecting the file `pom.xml`.
After all dependencies are resolved, Press the `run` button to build and run the web application on the `localhost`.

#### Using Maven (CLI)
If *Maven* is installed so we can build and run the application as follows.
##### Building the Runnable `.jar`
From the project's root folder which contain the  file `pom.xml`, we run the following command:
```shell
mvn install
```
Now a runnable `.jar` is created with the path `refinv-0.0.1-SNAPSHOT.jar` So we can execute it as follows:
```shell
java -jar target/refinv-0.0.1-SNAPSHOT.jar
```
Now the application is running on [localhost](http://localhost:8080/).


### Initilizer

I implemented an initilizer which create random users at the start of the application.

*More customizations required !*


## Resources

## Features / Perspectives
### Endpoints
#### Magic Link
Example:

[http://localhost:8080/invite?code=AXE-120260](http://localhost:8080/invite?code=AXE-12026)
### Score Logic
#### Formule
```
Score += 1 / lev;
```
Where `lev` is the length of the transitive invitation. 
### Online Status
To be implemented !