
FROM amazoncorretto:17
WORKDIR /app
COPY build/libs/User-service-v1.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]
