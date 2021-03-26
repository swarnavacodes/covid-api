FROM openjdk:8
EXPOSE 9595
ADD ./target/covid-api-0.0.1-SNAPSHOT.jar covid-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/covid-api-0.0.1-SNAPSHOT"]