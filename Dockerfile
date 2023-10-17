FROM maven:3.8.7-openjdk-21 as builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:21-jre
WORKDIR /app
COPY --from=builder /app/target/todolist-1.jar ./app.jar
CMD ["java", "-jar", "app.jar"]