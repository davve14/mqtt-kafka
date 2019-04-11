# mqtt-kafka

## Building and running a docker image

### Clone the repo
`git clone https://github.com/davve14/mqtt-kafka.git`

`cd mqtt-kafka`

### Build the Image from the Dockerfile
`docker build -t daasp14/mqtt-kafka .`

### Yml file
Example file included: mqtt-kafka.yml, change environments variables according to your kafka and mqtt brokers.
     
### Run a new container
`docker-compose -f mqtt-kafka.yml up -d`
