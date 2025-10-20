package com.test.kafka_lab_1.controller;

import com.test.kafka_lab_1.kafka.JsonKafkaProducer;
import com.test.kafka_lab_1.payload.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JSONMessageController {

    private JsonKafkaProducer jsonKafkaProducer;

    public JSONMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish (@RequestBody User user){
        System.out.println("/publish"+user.getFirstName());
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Message Sent to Kafka Topic");
    }
}
