FROM openjdk:8
#WORKDIR /src/main/java/com/springboot/forent
#COPY /src/main/java/com/springboot/forent/ForentApplication.java .
#ENV CLASSPATH=/src/main/java/com/springboot/forent
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]