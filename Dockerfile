#Use an official Maven image to build the Spring Boot app
FROM maven:3.8.5-openjdk-17 AS build

#Copy the source code and build the application
COPY . .
RUN mvn clean package -DskipTests

#Use an official OpenJDK image to run the application
FROM openjdk:17.0.1-jdk-slim

#Copy the build JAR file from the build stage
COPY --from=build /target/businessmanagement-0.0.1-SNAPSHOT.jar businessmanagement.jar

#Expose port 8080
EXPOSE 8080

#Specify the command to run the application
ENTRYPOINT [ "java", "-jar", "businessmanagement.jar"]






