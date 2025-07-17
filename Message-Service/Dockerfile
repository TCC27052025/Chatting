<<<<<<< HEAD
# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom and source files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]

=======
# Use lightweight OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Create app directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/CreateContactService-0.0.1-SNAPSHOT.jar /app/app.jar

# Optional: Set environment variable for Spring profile if needed
# ENV SPRING_PROFILES_ACTIVE=docker

# Expose port used by the application
EXPOSE 7878

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
