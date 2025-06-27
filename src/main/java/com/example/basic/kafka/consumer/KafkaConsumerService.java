package com.example.basic.kafka.consumer;

import com.example.basic.user.User;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;


@Service
@EnableKafka
public class KafkaConsumerService {
//    @KafkaListener(topics = "my_topic", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Received Message: " + message);
//    }

    // Lắng nghe dữ liệu từ topic và tự động deserialize thành đối tượng User
    @KafkaListener(topics = "my_topic", groupId = "group_id")
    public void consume(User user) {
        System.out.println("✅ Received Message: " + user.getUsername());
    }

    // Lắng nghe các partition cụ thể của topic
    @KafkaListener(topics = "my_topic", groupId = "group_id",
            topicPartitions = @TopicPartition(topic = "my_topic", partitions = { "1" }))
    public void listenPartition(ConsumerRecord<String, User> record) {
        User user = record.value();
        System.out.println("📌 Nhận dữ liệu từ Partition: " + record.partition() +
                ", Offset: " + record.offset() +
                ", Key: " + record.key() +
                ", User Username: " + user.getUsername() + ", Id: " + user.getId());
    }

    @KafkaListener(topics = "output_topic", groupId = "group_id")
    public void listenTopicStream(String message) {
        System.out.println("✅ Received Stream Message: "+message);
    }

}
