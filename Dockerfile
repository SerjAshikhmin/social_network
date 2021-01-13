FROM openjdk:11
ADD application/build/libs/application-1.0-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","application.jar"]
