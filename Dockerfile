FROM amazoncorretto:17-alpine-jdk
WORKDIR /opt
ENV PORT 8081
COPY target/*.jar /opt/blog-app.jar
ENTRYPOINT ["java","-jar","blog-app.jar"]