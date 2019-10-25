FROM openjdk:8u191-jdk-alpine3.9
ADD target/solvro-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar solvro-0.0.1-SNAPSHOT.jar
###ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "solvro-0.0.1-SNAPSHOT.jar"]
##FROM openjdk:8u191-jdk-alpine3.9
##ADD ../../../target/solvro-0.0.1-SNAPSHOT.jar .
###RUN  bash -c 'touch /app.jar'
###ENTRYPOINT["java","-Djava.security.egd=file:/dev/ ./urandom","-Dspring.profiles.active=container","-jar","app.jar"]
##ENTRYPOINT ["start.sh"]
#WORKDIR /app
#VOLUME ["/app"]
#COPY maven/app.jar app.jar
#COPY maven/start.sh start.sh
#COPY maven/wait-for-it.sh wait-for-it.sh
#RUN sh -c 'touch app.jar'
#ENTRYPOINT ["./start.sh"]