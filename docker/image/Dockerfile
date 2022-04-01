FROM maven:3-amazoncorretto-17

LABEL description="Image for running the cookie service"

ENV MAVEN_OPTS="-Xmx512m -Djava.awt.headless=true"

ENV TERM="xterm-256color"

ENV HOME=/usr/src/temi-callback
WORKDIR $HOME

RUN yum update -y --security
RUN yum install -y git
RUN git clone -b cookie-service --depth 1 https://github.com/kaygee/temi-callback.git $HOME
RUN mvn verify clean --fail-never

EXPOSE 7331
