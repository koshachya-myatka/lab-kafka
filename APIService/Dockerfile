FROM maven:latest AS build
WORKDIR /apiservice
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /apiservice
COPY --from=build /app/target/*.jar ./apiservice.jar
ENTRYPOINT ["java", "-jar", "apiservice.jar"]