FROM gradle:8.8.0-jdk21 AS build
COPY --chown=gradle:gradle .. /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:21-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/user-management-service.jar
CMD ["java", "-jar", "/app/user-management-service.jar"]