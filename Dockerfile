# Stage 1: Build the JAR using Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the Application
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar

# Render uses the PORT environment variable
EXPOSE 8080

# CRITICAL: -Xmx300m keeps Java inside Render's 512MB limit
ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]