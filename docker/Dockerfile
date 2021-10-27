FROM openjdk:8

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
ADD . $APP_HOME
RUN ./gradlew build
