FROM openjdk:8
ADD target/javabrains-spring-security.jar javabrains-spring-security.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "javabrains-spring-security.jar"]