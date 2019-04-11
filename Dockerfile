FROM maven:3.6.0-jdk-8-alpine

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies

# Bundle app source
COPY . .

RUN mvn clean package

#EXPOSE 80
CMD [ "java", "-jar", "target/test-consumer-0.0.1-SNAPSHOT.jar" ]