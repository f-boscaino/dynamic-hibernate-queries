FROM amazoncorretto:17

ARG JAR_FILENAME=target/dynamic-queries.jar

WORKDIR /app

COPY ${JAR_FILENAME} app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]