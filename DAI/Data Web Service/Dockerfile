FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
ARG JAR_NAME=sep4-service
COPY ${JAR_FILE} ${JAR_NAME}.jar
ENTRYPOINT ["java","-jar","/sep4-service.jar"]