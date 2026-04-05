# Stage 1: Build the JAR using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the Application
# Using 'jre' instead of 'jdk' because we only need to run the app, not compile it
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /target/*.jar app.jar

# Render uses the PORT environment variable
EXPOSE 8080

# Keep that -Xmx300m to avoid the "Out of Memory" monster
ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]