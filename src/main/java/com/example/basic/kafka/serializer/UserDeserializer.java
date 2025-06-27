package com.example.basic.kafka.serializer;

import com.example.basic.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserDeserializer implements Deserializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Cấu hình (nếu cần)
    }

    @Override
    public User deserialize(String topic, byte[] data) {
        try{
            // Deserialize mảng byte thành object User
            return objectMapper.readValue(data, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing User object", e);
        }
    }
}
