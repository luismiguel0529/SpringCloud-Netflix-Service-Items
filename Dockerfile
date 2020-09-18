FROM openjdk:14
VOLUME /tmp
EXPOSE 8761
ADD ./target/springboot-servicio-item-0.0.1-SNAPSHOT.jar items-server.jar
ENTRYPOINT ["java","-jar","/items-server.jar"]