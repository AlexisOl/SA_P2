FROM bellsoft/liberica-openjdk-alpine:21.0.3

WORKDIR /app

COPY target/mcsv-cines.jar /app/mcsv-cines.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "mcsv-cines.jar"]
