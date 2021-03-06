FROM maven:3.8.2-jdk-11-slim as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package
FROM openjdk:11.0.1-jdk-slim
WORKDIR /verbalcalculator
COPY ./data ./data
COPY ./execute.sh ./execute.sh
COPY ./config.json ./config.json
COPY --from=maven ./target ./target
CMD ["bash", "execute.sh"]
