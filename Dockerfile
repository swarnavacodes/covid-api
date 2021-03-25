FROM openjdk:8
EXPOSE 9595
ADD target/covid-api-0.0.1-SNAPSHOT
ENTRYPOINT ["java","-jar","/covid-api-0.0.1-SNAPSHOT"]