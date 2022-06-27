FROM registry.access.redhat.com/ubi8/openjdk-11:1.13

COPY . /home/jboss

USER root

RUN mvn clean package

WORKDIR /home/jboss

USER 185

ENTRYPOINT [ "java", "-jar", "target/app.jar" ]