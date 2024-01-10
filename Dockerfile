FROM openjdk:17-jdk

COPY target/test_series_api.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "test_series_api.jar"]
