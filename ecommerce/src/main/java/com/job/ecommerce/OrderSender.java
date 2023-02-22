package com.job.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSender {

    private static final Logger LOG = LoggerFactory.getLogger(OrderSender.class);

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${app.topic.example}")
    private String topic;

    public void send(Order data){
        LOG.info("sending data='{}' to topic='{}'", data, topic);

        Message<Order> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        
        kafkaTemplate.send(message);
    }

}
