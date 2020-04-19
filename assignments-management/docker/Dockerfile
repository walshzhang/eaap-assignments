FROM maven:3.6-jdk-8-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package -Dmaven.test.skip
RUN mkdir -p target/dependency && cd target/dependency && jar -xf ../assignments-management*.jar

FROM openjdk:8-jre-alpine
ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app/classes

ENTRYPOINT ["java", "-cp", ".:classes:lib/*", "iteach.eaap.assignments.management.AssignmentManagementApplication"]
