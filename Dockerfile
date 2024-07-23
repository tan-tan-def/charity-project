FROM maven:3-openjdk-17 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests


# Run stage

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/assignment01-0.0.1-SNAPSHOT.jar charity-project.jar
EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://tiny.db.elephantsql.com:5432/wzyswqhm
ENV SPRING_DATASOURCE_USERNAME=wzyswqhm
ENV SPRING_DATASOURCE_PASSWORD=a1LTx5nn42GGSSDNEspv58Btb4uO47WD

#ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SERVER_PORT=8082
ENV SPRING_MAIN_BANNER_MODE=off
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENTRYPOINT ["java","-jar","charity-project.jar"]