# Temi Callback Mock Service

I'm a callback mock service for http://www.rev.ai. I'll start up and listen on port 8080. Different paths return different HTTP status codes. I store jobs I've received in an in memory database and can return information on the jobs.

I use an in-memory database to keep track of all the callback requests I get.

## Technology
- Spring Boot (https://spring.io/projects/spring-boot)
- H2 (http://www.h2database.com/html/main.html)
- Rest (https://en.wikipedia.org/wiki/Representational_state_transfer)
- Spring JPA (https://projects.spring.io/spring-data-jpa/)
- Rev.ai API (https://www.rev.ai/)
- Jackson (https://github.com/FasterXML/jackson)

## Assumptions
- Installed homebrew (http://brew.sh/).

## When you have Homebrew
- `brew install maven`

## Build
- `mvn package`

## Build and Run Application
- `mvn clean spring-boot:run`

## Build Package Without Running Integration Tests
- `mvn -Dmaven.test.skip=true clean package` 

## Run
- `java -jar ./target/temi-callback-mock-0.1.0.jar`

## Methods
### Management related
- `/jobs/all` - Return all the jobs that have been received.
- `/jobs/count` - Return the count of jobs stored in the database.
- `/jobs/failed` - Return all the failed jobs.
- `/jobs/transcribed` - Return all the transcribed jobs.
### Callback related
These all assume that the payload complies with https://api.temi.com/api/reference/v1 or https://rev.ai.

All paths return the HTTP status code of the name of the path. For example ``/successful` returns HTTP 200.
- `/successful`

## Examples
### Endpoint Examples
- http://localhost:8080/jobs/all
- http://localhost:8080/jobs/transcribed
- http://localhost:8080/jobs/failed
### Integration Tests
- See the class `IntegrationTest.java` for examples.