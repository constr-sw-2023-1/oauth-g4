# OAuth API (G4)

### :warning: Requirements
* Java 17
* Docker
* Gradle

### :cloud: Cloud Endpoints
The following URLs are hosted on OCI, the following links can redirect you to it.
- [OAuth API](http://168.75.107.143:8080/).
- [Keycloak Admin](http://168.75.107.143:8090/).

### :safety_pin: Sample Users

- Username: admin@pucrs.br
- Password: admin

### :whale2: Docker Image
- The docker [OAuth image](https://github.com/constr-sw-2023-1/oauth-g4/pkgs/container/oauth-g4) can be pulled from the GHCR.

### :open_book: API Docs
- You can access [Swagger](http://168.75.107.143:8080/swagger-ui/index.html) to check the API docs.

### 🏥 API Health
- You can access [Health Monitor](http://168.75.107.143:8080/actuator/health) to check the API health.

### ⬇️ Validation Endpoint
- To validate your own token, you should call the */token/validate* endpoint
- If it is valid it will return an 204 No Content
- If it is invalid it will return an 401 Unauthorized

## :hammer_and_wrench: Building the project
Use the following command to build the project:
```
./gradlew build
```

## :runner: Running the Project
Use the following command to run the project:
```
java -jar build/libs/oauth-g4-1.0.0.jar
```

## :running_woman: Running the Project with Docker Compose
Use the following commands to run the project with Docker:
```
make build
make up
```
