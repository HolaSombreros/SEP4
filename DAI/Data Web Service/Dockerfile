FROM openjdk:11
ARG JAR_FILE=target/*.jar
ARG JAR_NAME=sep4-service
COPY ${JAR_FILE} ${JAR_NAME}.jar
EXPOSE 8888
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar","/sep4-service.jar"]