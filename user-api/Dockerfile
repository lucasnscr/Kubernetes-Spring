FROM azul/zulu-openjdk-alpine:11
VOLUME /tmp
ARG JAR_FILE=target/user-api-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]