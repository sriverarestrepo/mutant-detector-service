FROM openjdk:8-jdk-slim
COPY "./target/mutant-0.0.1-SNAPSHOT.jar" "meli-challenge-mutant-api.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","meli-challenge-mutant-api.jar"]