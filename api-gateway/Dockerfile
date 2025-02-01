
FROM amazoncorretto:21.0.4-alpine3.18

COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
