# Temi Callback Mock Service

I'm a callback mock service for https://www.temi.com/api. It'll start up and listen on port 8080. Different paths return different HTTP status codes.

## Assumptions
- Installed homebrew (http://brew.sh/).

## When you have Homebrew
- `brew install maven`

## Build
- `mvn package`

## Run
- `java -jar ./target/temi-callback-mock-0.1.0.jar`