
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Main {

	public static void main(String[] args) {
		Properties props = new Properties();
	 	props.put("bootstrap.servers", "192.168.39.52:9092");
	    props.put("group.id", "test");
	    props.put("enable.auto.commit", "true");
	    props.put("auto.commit.interval.ms", "1000");
	    props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
	    props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
	    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	    consumer.subscribe(Collections.singletonList("testtopic"));
	    
	    try {
		    while (true) {
		         ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
		         for (ConsumerRecord<String, String> record : records)
		             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		     }
	    }
	    finally {
	    	consumer.close();
	    }
	}
}