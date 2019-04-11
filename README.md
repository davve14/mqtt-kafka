# mqtt-kafka

## Building a Docker images

### Clone the repo
`git clone `

### Build the Image from the Dockerfile
`docker build -t daasp14/mqtt-kafka .`

### Yml file

version: '3'
services:
  mqtt-kafka:
    image: "daasp14/mqtt-kafka"
    environment:
     - KAFKA_BROKER_URI=http://192.168.99.100:9092
     - MQTT_SERVER_URI=tcp://192.168.99.100:1883
     
### Run a new container
`docker-compose -f mqtt-kafka.yml up -d`
