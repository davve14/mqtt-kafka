package streaming.converters;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerClient {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private KafkaProducer<String, String> producer;
	GetConfigPropertiesValues properties = new GetConfigPropertiesValues();
	
	public KafkaProducerClient() throws IOException {

		String kafkaBrokerURI;
		
		String env = System.getenv("KAFKA_BROKER_URI");
		if (env != null) {
			kafkaBrokerURI = env;
		}
		else {
			kafkaBrokerURI = properties.getPropValues("kafkaBrokerURI");
		}
		System.out.println("Using kafka broker on " + kafkaBrokerURI);
		
		Properties properties = new Properties();
		properties.put("bootstrap.servers", kafkaBrokerURI);
		properties.put("acks", "all");
		properties.put("linger.ms", 10);
		properties.put("retries", 0);
		properties.put("max.block.ms", 10000);
		properties.put("key.serializer", StringSerializer.class.getName());
		properties.put("value.serializer", StringSerializer.class.getName());
		
		producer = new KafkaProducer<>(properties);
		logger.debug("Finished initializing Kafka producer");	
	}
	
	public void send(String topic, String jsonMessage) {
		ProducerRecord<String,String> myrecord = new ProducerRecord<>(topic,jsonMessage);
		try {
			producer.send((ProducerRecord<String, String>) myrecord).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		producer.flush();
	}
	
	public void disconnect() {
		producer.close();
		logger.info("Kafka producer has been closed");
	}
}
