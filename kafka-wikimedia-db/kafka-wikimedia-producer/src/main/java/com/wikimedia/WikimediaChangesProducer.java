package com.wikimedia;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import okhttp3.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    private  static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic="wikimedia_recent_change";
        //To read real time stream data from wikimedia we need to use wikimedia
        EventHandler eventHandler= new WikimediaChangesHandler(kafkaTemplate,topic);
        String URL= "https://stream.wikimedia.org/v2/stream/recentchange";
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "KafkaProducerApp/1.0 (abc@example.com)"); // must include contact info

        EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(URL))
                .headers(Headers.of(headers))
                .build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);

    }

}
