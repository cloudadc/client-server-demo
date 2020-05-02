# Use openjdk 13 as a base image
FROM openjdk:13

# Set Label
LABEL maintainer "kylinsoong.1214@gmail.com"

# Set the working directory to /app
WORKDIR /app

# Copy the runable jar into the container at /app
ADD target/backend-0.0.2.jar /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run  when the container launches
CMD ["java", "-jar", "backend-0.0.2.jar"]
