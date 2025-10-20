package com.test.kafka_lab_1.kafka;

import com.test.kafka_lab_1.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, User> kakfaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kakfaTemplate) {
        this.kakfaTemplate = kakfaTemplate;
    }

    public void sendMessage(User data) {
        LOGGER.info(String.format("Message Sent-> %s", data.toString()));
        Message<User> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "chennai_json")
                .build();
        kakfaTemplate.send(message);
    }

}
