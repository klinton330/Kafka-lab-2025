package com.test.kafka_lab_1.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic testLabTopic(){
        return TopicBuilder.name("chennai").build();
    }

    @Bean
    public NewTopic testLabTopic_json(){
        return TopicBuilder.name("chennai_json").build();
    }
}
