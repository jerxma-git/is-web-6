# FROM maven:3.9.4-amazoncorretto-21 as BUILD
# WORKDIR /app
# COPY . .
# RUN mvn clean package 

FROM amazoncorretto:21-alpine-jdk
COPY target/todolist-1.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]