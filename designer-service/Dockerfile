
FROM amazoncorretto:21.0.4-alpine3.18
RUN apk add --no-cache bash
COPY target/designer-service-0.0.1-SNAPSHOT.jar designer-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/designer-service.jar"]
