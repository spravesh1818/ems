FROM adoptopenjdk:11-jre-hotspot
VOLUME /var/lib/data/ems
ADD release/EMS-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080