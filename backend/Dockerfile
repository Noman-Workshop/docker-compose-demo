FROM eclipse-temurin:19.0.2_7-jdk-jammy

# RUN apt update -y

# RUN apt install maven -y

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY buildNRun.sh ./

COPY src ./src

# # UPDATE DEPENDENCIES
# RUN mvn dependency:resolve || exit 0

# EXPOSE PORT
EXPOSE 8080

# RUN THE APP
ENTRYPOINT ["./buildNRun.sh"]