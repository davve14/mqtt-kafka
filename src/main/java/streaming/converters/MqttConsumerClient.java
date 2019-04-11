package streaming.converters;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConsumerClient {
	
	String queue;
	String topic;
	MqttClient client;
	GetConfigPropertiesValues properties = new GetConfigPropertiesValues();
	KafkaProducerClient producer;
	
	public MqttConsumerClient(String queue, KafkaProducerClient producer,String topic) throws MqttException, IOException {
		
		String mqttServerURI = properties.getPropValues("mqttServerURI");
		System.out.println("Starting subscriber...");
		this.producer = producer;
		this.topic = topic;
		
	    MqttClient client = new MqttClient(mqttServerURI, MqttClient.generateClientId());
	    client.setCallback( new MqttCallbackBridge(producer, topic) );
	    client.connect();

	    client.subscribe(topic);
	}
	
	public void disconnect() {
		try {
			client.disconnect();
			client.close();
			System.out.println("MQTT consumer {} has been closed");
		} catch (MqttException e) {
			System.out.println("Failed to close MQTT consumer {} " + e);
		}
}
}
