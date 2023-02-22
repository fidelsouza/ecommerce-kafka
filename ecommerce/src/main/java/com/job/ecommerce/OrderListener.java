package com.job.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {

    private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

    @KafkaListener(topics = "${app.topic.example}")
    public void receive(@Payload Order data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);

        headers.keySet().forEach(key -> {
        	System.out.println("Value received...:"+data.getValue());
            LOG.info("{}: {}", key, headers.get(key));
        });
    }
}
