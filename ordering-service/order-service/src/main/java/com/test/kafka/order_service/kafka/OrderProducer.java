package com.test.kafka.order_service.kafka;

import com.test.kafka.base_domains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger logger=LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic newTopic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        System.out.println("messafe");

        logger.info(String.format("Order Event => %s",orderEvent.toString()));
        Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC,newTopic.name())
                .build();
        //Publishing to MESSAGE BROKER
        kafkaTemplate.send(message);
    }
}
