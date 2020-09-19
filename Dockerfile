FROM openjdk:14
VOLUME /tmp
ADD ./target/springboot-servicio-item-0.0.1-SNAPSHOT.jar items-server.jar
ENTRYPOINT ["java","-jar","/items-server.jar"]