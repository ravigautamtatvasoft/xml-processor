FROM openjdk:17
WORKDIR /opt
COPY target/*.jar /opt/xml-processor.jar
ENTRYPOINT ["java", "-jar", "xml-processor.jar"]
