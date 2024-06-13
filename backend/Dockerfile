FROM maven:3.9.7-amazoncorretto-21 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package

FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=builder /app/target/ecommerce-0.0.1-SNAPSHOT.jar ./app.jar
COPY wait-for.sh .

EXPOSE 8080

ENTRYPOINT ["java","-jar","./app.jar"]

