package com.java.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

	public static void main(String[] args) {
		var consumer = new KafkaConsumer<String, String>(properties());
		// consumir as mesagens

		consumer.subscribe(Collections.singletonList("compras.do.cliente"));

		while (true) {
			// What time in time to consumer
			var records = consumer.poll(Duration.ofMillis(100));
			for (var record : records) {
				System.out.println("Compra nova:");
				System.out.println(record.key());
				System.out.println(record.value());
				System.out.println(record.partition());
				System.out.println(record.offset());
			}
		}
	}

	private static Properties properties() {
		Properties properties = new Properties();
		// Where kafka is running
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		// to Serialize the key
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		// to Serialize the value
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumo-cliente");
		return properties;
	}
}
