# Temi Callback Mock Service

I'm a service that can store browser cookies! I'll start up and listen on port 7331. I store jobs I've received in 
an in memory database and can return information on them.

I use an in-memory database to keep track of all the callback requests I get.

## Technology
- Spring Boot (https://spring.io/projects/spring-boot)
- H2 (http://www.h2database.com/html/main.html)
- Rest (https://en.wikipedia.org/wiki/Representational_state_transfer)
- Spring JPA (https://projects.spring.io/spring-data-jpa/)
- Jackson (https://github.com/FasterXML/jackson)

## Assumptions
- Installed homebrew (http://brew.sh/).

## When you have Homebrew
- `brew install maven`

## Build JAR
- `mvn package`

## Build Package Without Running Integration Tests
- `mvn -Dmaven.test.skip=true clean package` 

## Run Using Packaged JAR
- `java -jar ./target/temi-callback-mock-0.1.0.jar`

## Build and Run Application
- `mvn clean spring-boot:run`

## Run in Docker container
- Change to the `docker` directory.
- `docker-compose up cookies-service --rm`

## Debugging in IntelliJ
To debug the application locally set your breakpoint, right-click the `main` method, and choose `Debug`.

`public static void main(String[] args) {SpringApplication.run(Application.class, args);}` 

## Methods
- Health
-- `/health` - Is a health check for the application.
- Cookies related methods
-- DELETE - `/cookies/{role}/{environment}/delete` - delete the cookies.
-- GET - `/cookies/{role}/{environment}/exists` - do cookies exist?
-- GET - `/cookies/{role}/{environment}/get` - get the cookies.
-- POST - `/cookies` - save the cookies.
