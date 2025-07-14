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
