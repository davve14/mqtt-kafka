package streaming.converters;

import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

	public static void main(String[] args) throws IOException, MqttException {

		KafkaProducerClient producer = new KafkaProducerClient();
		new MqttConsumerClient("iot_data",producer,"testtopic");
	}
}