FROM openjdk:19
COPY target/healthtrio-1.0.0-with-dependencies.jar healthtrioApp.jar
ENTRYPOINT ["java","-jar","healthtrioApp.jar"]