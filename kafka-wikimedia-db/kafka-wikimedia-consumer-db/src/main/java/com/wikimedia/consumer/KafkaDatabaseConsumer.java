package com.wikimedia.consumer;

import com.wikimedia.entity.WikimediaData;
import com.wikimedia.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(topics="${spring.kafka.topic.name}",groupId = "${spring.kafka.group-id}")
    public void consume(String eventMessage){
       LOGGER.info(String.format("Message Received -> %s",eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikieventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);
    }
}
