
![ui](https://github.com/Redeem-Grimm-Satoshi/CRMS/assets/45304978/9ea3030f-7d39-4a7b-859a-bd6077df644d)


## Customer Relationship Management System ( CRMS )
This CRMS is built using Java, Vaadin and SpringBoot Framework

## Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/crms-1.0-SNAPSHOT.jar`

## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). 
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.


To build the Dockerized version of the project, run

```
mvn clean package -Pproduction
docker build . -t crms:latest
```

## Deploying using Docker

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 crms:latest
```

NB: Can be deployed to Heroku
