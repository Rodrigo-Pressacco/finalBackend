FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=./target/configServer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
RUN apk --no-cache add curl
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8888
