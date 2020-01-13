# Temi Callback Mock Service

I'm a callback mock service for http://www.temi.com/api and http://www.rev.ai. It'll start up and listen on port 8080. Different paths return different HTTP status codes. I store jobs I've received in an in memory database and can return information on the jobs.

I use an in-memory database to keep track of all the callback requests I get.

## Technology
- Spring Boot (https://spring.io/projects/spring-boot)
- H2 (http://www.h2database.com/html/main.html)
- Rest (https://en.wikipedia.org/wiki/Representational_state_transfer)
- Spring JPA (https://projects.spring.io/spring-data-jpa/)
- Temi API (https://www.temi.com/api)
- Rev.ai API (https://www.rev.ai/)
- Jackson (https://github.com/FasterXML/jackson)

## Assumptions
- Installed homebrew (http://brew.sh/).

## When you have Homebrew
- `brew install maven`

## Build
- `mvn package`

## Build Package Without Running Integration Tests
- `mvn -Dmaven.test.skip=true package` 

## Run
- `java -jar ./target/temi-callback-mock-0.1.0.jar`

## Methods
### Management related
- `/jobs` - Return all the jobs that have been received.
- `/jobs/{id}` - Return the job(s) that have the corresponding jobId.
- `/jobs/{status}/status` - Return the job(s) that have the corresponding status.
### Callback related
These all assume that the payload complies with https://api.temi.com/api/reference/v1 or https://rev.ai.

All paths return the HTTP status code of the name of the path. For example ``/successful` returns HTTP 200.
- `/successful`
- `/bad-request`
- `/unauthorized`
- `/internal-server-error`
- `/gone`

### Parameters
### Management related
- `/jobs` - has two optional parameters.
    - `type` which is the type of job.
    - `status` which is the staus of the job.

## Examples
- http://localhost:8080/jobs?type=revai&status=transcribed
- http://localhost:8080/jobs?type=temi
- http://localhost:8080/jobs?status=failed
