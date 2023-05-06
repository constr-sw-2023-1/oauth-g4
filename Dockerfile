FROM gradle:latest AS builder

WORKDIR /app

COPY . .

RUN gradle build

FROM openjdk:17-ea-17-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
