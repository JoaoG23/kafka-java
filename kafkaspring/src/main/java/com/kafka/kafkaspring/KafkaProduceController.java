package com.kafka.kafkaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProduceController {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@GetMapping("/producer/{message}")
	public void producer(@PathVariable("message") String message) {
		this.kafkaTemplate.send("ecommerce.cliente2", message);
	}
}
