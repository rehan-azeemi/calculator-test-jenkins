FROM openjdk:8
EXPOSE 9090
ADD target/calculator-jenkins-docker.jar calculator-jenkins-docker.jar
ENTRYPOINT ["java","-jar","calculator-jenkins-docker.jar"]