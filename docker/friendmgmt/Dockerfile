#FROM maven:3.5.0-jdk-8-alpine
#
#WORKDIR /code
#
## Prepare by downloading dependencies
#ADD pom.xml /code/pom.xml
#RUN ["mvn", "dependency:go-offline"]
#
## Adding source, compile and package into a fat jar
#ADD src /code/src
#RUN ["mvn", "package", "-Dmaven.test.skip=true"]

#
#EXPOSE 8080
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/friendmgmt-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jdk-alpine
ADD target/friendmgmt-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]