FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src
RUN mvn clean install -DskipTests


FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build /app/target/tech-challenge-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_APP_ARGS="--spring.config.location=/src/main/resources/application.yml"
ENTRYPOINT ["java","-jar","app.jar", "$JAVA_APP_ARGS"]