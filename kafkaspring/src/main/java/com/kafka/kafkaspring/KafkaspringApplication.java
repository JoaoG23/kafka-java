package com.kafka.kafkaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaspringApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaspringApplication.class, args);
	}

}
