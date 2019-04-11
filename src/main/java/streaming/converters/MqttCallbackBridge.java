package streaming.converters;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallbackBridge implements MqttCallback {

	KafkaProducerClient producer;
	String topic;
	
	public MqttCallbackBridge(KafkaProducerClient producer, String topic) {
		this.producer = producer;
		this.topic = topic;
	}
	
	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection to MQTT broker lost!");
	}
	
	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		System.out.println("Message received:\t"+ new String(mqttMessage.getPayload()) );
		producer.send(topic, new String(mqttMessage.getPayload()));
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivery complete...");
	}
	
}
