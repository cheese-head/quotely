FROM  gradle:8.7.0-jdk21 as build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
COPY . .
RUN gradle build --no-daemon 
RUN "ls"

FROM eclipse-temurin:21

COPY --from=build /home/gradle/src/app/build/libs/*.jar /opt/quotely.jar

ENTRYPOINT ["java", "-jar", "/opt/quotely.jar"]