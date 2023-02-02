#!/bin/bash

# # UPDATE DEPENDENCIES
# mvn dependency:resolve

# CREATE THE JAR
./mvnw package -f pom.xml

# RUN THE APPLICATION
java -jar target/taxcalculation-0.0.1-SNAPSHOT.jar