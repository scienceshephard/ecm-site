FROM maven:3.9-sapmachine-21 AS mavenbuild

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-ea-1-slim

COPY --from=mavenbuild /target/ecm-site-0.0.1-SNAPSHOT.jar ecm-site.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ecm-site.jar"]
