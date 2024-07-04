package com.kafka.kafkaspring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = "${app.topic.foo}", groupId = "ecommerce-groupId")
	public void consumer(Produto produto) {
		System.out.println(produto);
	}
}
