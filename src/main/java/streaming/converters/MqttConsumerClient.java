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
		
		String mqttServerURI;
		
		String envServer = System.getenv("MQTT_SERVER_URI");
		if (envServer != null) {
			mqttServerURI = envServer;
		}
		else {
			mqttServerURI = properties.getPropValues("mqttServerURI");
		}
				
		System.out.println("Subscribing to " + queue + " on server " + mqttServerURI);
		this.producer = producer;
		this.topic = topic;
		
		@SuppressWarnings("resource")
		MqttClient client = new MqttClient(mqttServerURI, MqttClient.generateClientId());
	    client.setCallback( new MqttCallbackBridge(producer, topic) );
	    try {
	    	client.connect();
	    	client.subscribe(queue);
	    }
	    catch (Exception e) {
	    	System.out.println(e);
	    }
	}
}
