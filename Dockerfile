FROM openjdk:8 AS builder
ENV BUILD_HOME=/usr/app/builder
WORKDIR $BUILD_HOME
COPY build.gradle* settings.gradle* gradle* $BUILD_HOME/
COPY gradle $BUILD_HOME/gradle
RUN ./gradlew build || return 0
COPY . ./
RUN ./gradlew installDist

FROM openjdk:8-jre-alpine AS runner
ENV BUILD_HOME=/usr/app/builder
ENV RUN_HOME=/usr/app/runner
ENV SERVER=bar-service-server
ENV BUILD_PATH=build
ENV EXECUTABLE=bar-server
WORKDIR $RUN_HOME
COPY --from=builder $BUILD_HOME/$SERVER/$BUILD_PATH/install/ ./
EXPOSE 50051
CMD ./$SERVER/bin/$EXECUTABLE