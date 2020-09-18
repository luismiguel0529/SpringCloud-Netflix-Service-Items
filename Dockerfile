FROM openjdk:14
VOLUME /tmp
EXPOSE 8761
ADD ./target/springboot-servicio-item-0.0.1-SNAPSHOT.jar item-server.jar
ENTRYPOINT ["java","-jar","/productos-server.jar"]