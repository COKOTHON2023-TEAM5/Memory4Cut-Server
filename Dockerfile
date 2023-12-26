FROM amd64/amazoncorretto:17
COPY ./build/libs/Memory-0.0.1-SNAPSHOT.jar APPLICATION.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "APPLICATION.jar"]