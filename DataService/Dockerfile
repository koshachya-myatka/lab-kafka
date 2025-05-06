FROM maven:latest AS build
WORKDIR /dataservice
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /dataservice
COPY --from=build /dataservice/target/*.jar ./dataservice.jar
ENTRYPOINT ["java", "-jar", "dataservice.jar"]