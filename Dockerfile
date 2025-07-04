FROM openjdk:21-jdk
LABEL authors="Daniel"
COPY build/libs/sistema-registro-escolar-mid-0.0.1-SNAPSHOT.jar sre-back-app.jar
ENTRYPOINT ["java", "-jar", "sre-back-app.jar"]