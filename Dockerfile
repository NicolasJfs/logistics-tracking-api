FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/logistics-tracking-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]