FROM eclipse-temurin:17-jdk-alpine
COPY target/CRUDBlog-0.0.1-SNAPSHOT.jar CRUDBlog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/CRUDBlog-0.0.1-SNAPSHOT.jar"]
