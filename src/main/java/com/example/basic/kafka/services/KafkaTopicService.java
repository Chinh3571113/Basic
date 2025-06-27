package com.example.basic.kafka.services;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;


@Service
public class KafkaTopicService {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public void getTopicPartitions(String topicName) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(props)) {
            // Kiểm tra topic có tồn tại không
            ListTopicsResult topicsResult = adminClient.listTopics();
            Set<String> topics = topicsResult.names().get();
            // Show danh sách topic
            System.out.println("✅ Topics: " + topics);

            if (!topics.contains(topicName)) {
                System.err.println("❌ Topic '" + topicName + "' không tồn tại!");
                return;
            }

            // Lấy thông tin partition
            Map<String, TopicDescription> topicDescriptions = adminClient.describeTopics(Collections.singletonList(topicName)).all().get();
            TopicDescription topicDescription = topicDescriptions.get(topicName);

            System.out.println("✅ Topic: " + topicName);
            for (TopicPartitionInfo partitionInfo : topicDescription.partitions()) {
                System.out.println("Partition: " + partitionInfo.partition() +
                        ", Leader: " + partitionInfo.leader() +
                        ", Replicas: " + partitionInfo.replicas());
            }
        } catch (InvalidTopicException e) {
            System.err.println("❌ Lỗi: Tên topic không hợp lệ - " + e.getMessage());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
