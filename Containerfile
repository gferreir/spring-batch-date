FROM registry.access.redhat.com/ubi8/openjdk-11:1.13

COPY pom.xml /home/jboss
COPY src /home/jboss

RUN mvn clean package

ENTRYPOINT [ "java", "-jar", target/app.jar ]