FROM maven:3.8.5-openjdk-17 AS mavenbuild

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=mavenbuild /target/ecm-site-0.0.1-SNAPSHOT.jar ecm-site.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ecm-site.jar"]
