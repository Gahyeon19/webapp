# 실행 단계: 경량화된 JRE 이미지에서 애플리케이션 실행
FROM eclipse-temurin:17-jre-alpine

# ./gradlew clean build 로 빌드된 JAR 파일을 실행 단계로 복사
COPY ./build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8080

# JAR 파일 실행
CMD ["java", "-jar", "app.jar"]