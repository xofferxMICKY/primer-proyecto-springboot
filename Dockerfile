FROM maven:3.9.6-amazoncorretto-21 AS build

COPY ./ /app
WORKDIR /app

RUN mvn clean package -DskipTests

FROM amazoncorretto:21

COPY --from=build /app/target/proyecto_venta_offer_esteban-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
