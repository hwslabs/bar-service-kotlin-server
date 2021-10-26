FROM openjdk:8 AS builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle* settings.gradle* gradle* $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew installDist

FROM openjdk:8 AS runner
ENV APP_HOME=/usr/app/
ENV SERVER_PATH=starter-service-server
ENV BUILD_PATH=build
ENV EXECUTABLE_NAME=starter-server
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/$SERVER_PATH/$BUILD_PATH/ .
EXPOSE 50051
CMD ./install/$SERVER_PATH/bin/$EXECUTABLE_NAME
