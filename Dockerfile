FROM openjdk:17-ea-17-jdk-slim

EXPOSE 8080

COPY /build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# runs the healthcheck every 5 seconds and waits 10 seconds before starting the first check
# this checks if the application is running correctly
HEALTHCHECK --start-period=10s --interval=5s --timeout=3s \
    CMD curl -f http://localhost:8080/actuator/health || exit 1
