FROM maven:3.8.1-openjdk-17-slim as builder

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn clean package -DskipTests


FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/simple-bank-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java","-jar","./simple-bank-0.0.1-SNAPSHOT.jar"]