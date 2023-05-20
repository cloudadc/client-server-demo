# Use openjdk 17 as a base image
FROM openjdk:17-jdk-slim

# Set Label
LABEL maintainer "kylinsoong.1214@gmail.com"

# Set the working directory to /app
WORKDIR /app

# Copy the runable jar into the container at /app
ADD target/backend-0.1.5.jar /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

ENV APP_DATACENTER_NAME="local"
ENV APP_VERSION_NUMBER="0.0"
ENV APP_DECISION_BI_RPT="BI"
ENV APP_REDIRECT_ABSOLUTE_PATH="true"

# Run  when the container launches
CMD ["java", "-jar", "backend-0.1.5.jar"]
