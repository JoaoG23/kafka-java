package com.java.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		var producer = new KafkaProducer<String, String>(properties());
//		var record = new ProducerRecord<String, String>("compras.do.cliente", "cliente-1", "compras:50reais");

		Callback callback = (data, ex) -> {

			if (ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println("Enviado com sucesso");
			System.out.println(data.partition());
			System.out.println(data.offset());
		};

		for (int i = 0; i < 3; i++) {
			var record = new ProducerRecord<String, String>("ecommerce.compras.teste", "cliente" + i, "compras:" + i + "reais");
			producer.send(record, callback).get();
		}

//		producer.send(record, callback).get();

	}

	private static Properties properties() {
		Properties properties = new Properties();
		// Where kafka is running
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		// to Serialize the key
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// to Serialize the value
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}
}
