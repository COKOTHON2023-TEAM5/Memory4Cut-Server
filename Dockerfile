FROM amd64/amazoncorretto:17
WORKDIR /app
COPY ./build/libs/Memory-0.0.1-SNAPSHOT.jar /app/APPLICATION.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "APPLICATION.jar"]