
FROM amazoncorretto:21.0.4-alpine3.18

COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]
