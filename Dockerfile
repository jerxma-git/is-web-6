FROM adoptopenjdk:21-jre-alpine
WORKDIR /app
COPY target/todolist-1.jar ./app.jar
CMD ["java", "-jar", "app.jar"]