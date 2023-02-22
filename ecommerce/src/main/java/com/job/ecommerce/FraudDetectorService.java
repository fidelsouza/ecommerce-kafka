package com.job.ecommerce;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class FraudDetectorService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var consumer = new KafkaConsumer<>(properties());
		consumer.subscribe(Collections.singletonList("NOVO_PEDIDO"));
		
		System.out.println("Iniciando consumo...");

		while (true) {
			var records = consumer.poll(Duration.ofMillis(2000));
			for (var record : records) {
				System.out.println("----------------------");
				System.out.println("Processing new order, checking for fraud");
				System.out.println("Key:"+record.key());
				System.out.println("Value:"+record.value());
				System.out.println(record.partition());
				System.out.println(record.offset());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Order processed!");
			}
		}

	}

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());
		return properties;
	}

}
