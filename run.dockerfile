# VERSION 1.0

# the base image is a trusted ubuntu build with java 8 (https://index.docker.io/u/dockerfile/java/)
FROM openjdk:8-jre-alpine

# that's me!
MAINTAINER Samlet Wu, xiaofei.wu@gmail.com

# we need this because the workdir is modified in dockerfile/java
WORKDIR /app

# run the (java) server as the daemon user
# USER daemon

# ADD data /app/data
# copy the locally built fat-jar to the image
ADD target/appassembler /app/appassembler

# run the server when a container based on this image is being run
ENTRYPOINT [ "sh", "/app/appassembler/bin/app" ]

# the server binds to 10052 - expose that port
EXPOSE 10052
