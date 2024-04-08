FROM maven:3.8.5-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-alpine
COPY --from=build /out/artifacts/todo1_jar/todo1.jar todo1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","todo1.jar"]