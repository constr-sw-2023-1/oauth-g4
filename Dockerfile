FROM gradle:jdk17-jammy AS builder

WORKDIR /app

COPY . .

RUN gradle build

FROM openjdk:21-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
