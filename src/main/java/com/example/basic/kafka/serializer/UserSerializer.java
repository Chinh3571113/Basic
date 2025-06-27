package com.example.basic.kafka.serializer;

import com.example.basic.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Cấu hình (nếu cần)
    }

    @Override
    public byte[] serialize(String topic, User data) {
        try {
            // Serialize object User thành mảng byte
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing User object", e);
        }
    }

    @Override
    public void close() {
        // Cleanup (nếu cần)
    }
}
