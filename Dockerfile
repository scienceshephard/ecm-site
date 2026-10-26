FROM maven:3.9.6-eclipse-temurin-21 AS mavenbuild
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=mavenbuild /app/target/ecm-site-0.0.1-SNAPSHOT.jar ecm-site.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecm-site.jar"]