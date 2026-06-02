FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:resolve

COPY . .

RUN mvn clean install -DskipTests

CMD ["mvn", "-Dtest=TestRunner", "test"]
