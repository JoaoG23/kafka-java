package com.kafka.kafkaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProduceController {
	@Autowired
	private KafkaTemplate<String, Produto> kafkaTemplate;
	@Value("${app.topic.foo}")
	private String topic;

	@GetMapping("/send/{message}/{name}")
	public void producer(@PathVariable("message") String message, @PathVariable("name") String name) {
		Produto produto = new Produto(message, name);
		kafkaTemplate.send(topic, produto);
		System.out.println("Message sent: " + produto);
	}
}
