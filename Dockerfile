
FROM openjdk:8-jdk-alpine

LABEL maintainer="anita.pal1687@gmail.com"

ARG JAR_FILE=target/Users-0.0.1-SNAPSHOT.war

EXPOSE 9001

COPY ${JAR_FILE} Users-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java","-jar","Users-0.0.1-SNAPSHOT.war"]