FROM maven:3-amazoncorretto-17

LABEL description="Image for running the cookie service"

ENV MAVEN_OPTS="-Xmx512m -Djava.awt.headless=true"

ENV TERM="xterm-256color"

RUN yum update -y --security
RUN yum install -y git
