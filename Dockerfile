FROM openjdk:8-jre-alpine

MAINTAINER karra radouane

WORKDIR /applications

COPY ms-cours3-1.0.0-SNAPSHOT.jar /applications/app.jar

ENTRYPOINT["java", "-jar", "/applications/app.jar"]