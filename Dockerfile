# First stage: build fat JAR

FROM maven:latest AS builder

WORKDIR /opt/app

COPY . .

RUN mvn clean verify -Dmaven.test.skip=true

###

# Second stage: run fat JAR
FROM openjdk:20

WORKDIR /opt/app


COPY --from=builder /opt/app/target/*.jar ./


EXPOSE 8082


ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /opt/app/*.jar