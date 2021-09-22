FROM openjdk:8
EXPOSE 9090
ADD target/calculator-docker.jar calculator-docker.jar
ENTRYPOINT ["java","-jar","calculator-docker.jar"]