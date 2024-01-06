FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/target/trip-management-system-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
