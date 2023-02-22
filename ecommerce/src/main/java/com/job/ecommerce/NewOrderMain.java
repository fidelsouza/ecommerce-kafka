package com.job.ecommerce;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class NewOrderMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		var producer = new KafkaProducer<String, Order>(properties());

		for (int i = 0; i < 100; i++) {

			var key = UUID.randomUUID().toString();
			var order = new Order(key, Math.random());

			var record = new ProducerRecord<>("NOVO_PEDIDO", key, order);
			producer.send(record, (data, ex) -> {
				if (ex != null) {
					ex.printStackTrace();
					return;
				}

				System.out.println("Sucesso enviando - :::Value:" + order.getId() + "-" + order.getValue()
						+ " :::topic:" + data.topic() + "" + " :::partition:" + data.partition() + " :::offset:"
						+ data.offset());
			}).get();

			Thread.sleep(2000);
		}

	}

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		return properties;
	}

}
