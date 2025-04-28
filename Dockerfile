FROM openjdk:17-slim

COPY ./base/target/base-1.0.0.jar /app/
WORKDIR /app

CMD ["java", "-jar", "base-1.0.0.jar"]