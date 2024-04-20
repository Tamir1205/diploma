FROM openjdk:17
ADD target/flatsharing.jar flatsharing.jar
ENTRYPOINT ["java","-jar","flatsharing.jar"]