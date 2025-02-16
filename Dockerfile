# Use a minimal JRE image
FROM eclipse-temurin:17-alpine

WORKDIR /opt/app

RUN apk add --no-cache curl wget tar unzip

ARG APP_ENV=default
ENV PROFILE=${APP_ENV}
ENV JAVA_OPTS="-Dspring.profiles.active=${PROFILE}"

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]