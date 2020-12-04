#FROM openjdk:8
#COPY target/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:8
LABEL maintainer="dquisido@ms3-inc.com"
#VOLUME /tmp
EXPOSE 8090
#ADD /target/forent-0.0.1-SNAPSHOT.jar forent-0.0.1-SNAPSHOT.jar
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
