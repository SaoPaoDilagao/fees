FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8093
ADD target/fees.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]