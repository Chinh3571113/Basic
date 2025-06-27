package com.example.basic.kafka.producer;

import com.example.basic.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "input_topic";

    // Gửi Message
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

}
