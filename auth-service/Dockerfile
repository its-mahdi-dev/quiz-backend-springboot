FROM amazoncorretto:21.0.4-alpine3.18
RUN apk add --no-cache bash
COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/auth-service.jar"]
