package com.test.kafka_lab_1.kafka;

import com.test.kafka_lab_1.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic_json.name}" ,groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(User user){
        LOGGER.info((String.format("Json Message received->%s",user.toString())));
    }
}
