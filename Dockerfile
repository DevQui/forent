FROM openjdk:8-jdk-alpine
LABEL maintainer="dquisido@ms3-inc.com"
#VOLUME /tmp
EXPOSE 8090
#ADD /target/forent-0.0.1-SNAPSHOT.jar forent-0.0.1-SNAPSHOT.jar
COPY /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
