package com.kafka.kafkaspring;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {
	@KafkaListener(topics = "ecommerce.cliente2", groupId = "ecommerce-groupId")
	public void consumer(String message) {
		System.out.println(message);
	}
}
