# Temi Callback Mock Service

I'm a callback mock service for https://www.temi.com/api. It'll start up and listen on port 8080. Different paths return different HTTP status codes.

I use an in-memory database to keep track of all the callback requests I get.

## Assumptions
- Installed homebrew (http://brew.sh/).

## When you have Homebrew
- `brew install maven`

## Build
- `mvn package`

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
- `/jobs` - has a parameter called `type` which can have the value of the type of job. Check the JobType enum.
