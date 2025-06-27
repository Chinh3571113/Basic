package com.example.basic.kafka.controllers;

import com.example.basic.kafka.consumer.KafkaConsumerService;
import com.example.basic.kafka.producer.KafkaProducerService;
import com.example.basic.kafka.services.KafkaTopicService;
import com.example.basic.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private KafkaTopicService kafkaTopicService;


    @PostMapping("/send/{message}")
    public String sendMessage(@PathVariable("message") String message) {
        kafkaProducerService.sendMessage(message);
        return "Message sent to Kafka: "+message;
    }

    @GetMapping("/getPartitions")
    public void getPartitions(@RequestBody String topicName) {
        String topicName2= "my_topic";
        kafkaTopicService.getTopicPartitions(topicName2);
    }


}
