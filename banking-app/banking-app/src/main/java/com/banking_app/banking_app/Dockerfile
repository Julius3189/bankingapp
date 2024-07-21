#Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

#Information around who maintains the image
MAINTAINER julius.com

# Add the application's jar to the image
COPY target/banking-app-0.0.1-SNAPSHOT.jar banking-app-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "banking-app-0.0.1-SNAPSHOT.jar"]