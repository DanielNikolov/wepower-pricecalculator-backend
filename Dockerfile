FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} pricecalculator.jar
ENTRYPOINT ["java", "-jar", "/pricecalculator.jar"]