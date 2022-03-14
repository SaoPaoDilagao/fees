FROM adoptopenjdk/openjdk8:alpine-slim
ADD target/fees-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8093
EXPOSE 27017
ENTRYPOINT ["java", "-jar","/app.jar"]