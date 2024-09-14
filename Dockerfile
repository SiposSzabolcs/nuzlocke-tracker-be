FROM jelastic/maven:3.9.4-openjdk-22.ea-b17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/target/nuzlocke-tracker-api-0.0.1-SNAPSHOT.jar /app/nuzlocke-tracker-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/nuzlocke-tracker-api.jar"]