FROM openjdk:11
EXPOSE 8081
ADD target/springboot.jar springboot.jar
ENTRYPOINT ["java","-jar","/springboot.jar"]
