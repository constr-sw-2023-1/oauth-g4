FROM arm64v8/gradle:6.9-jammy AS builder

WORKDIR /app

COPY . .

RUN gradle build

FROM arm64v8/openjdk:21

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
