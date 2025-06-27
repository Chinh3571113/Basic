package com.example.basic.kafka.services;


import com.example.basic.entities.WordCount;
import com.example.basic.repositories.WordCountRepository;
import com.example.basic.user.User;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

@Service
public class KafkaStreamProcessor {
    // Kafka Stream dùng để viết hoa tất cả dữ liệu
//    private final KafkaStreams kafkaStreams;
//    public KafkaStreamProcessor(Properties kafkaStreamsProperties) {
//        StreamsBuilder builder = new StreamsBuilder();
//        // Đọc dữ liệu từ topic "input_topic"
//        KStream<String, String> stream = builder.stream("input_topic");
//
//        // Chuyển đổi dữ liệu (ví dụ: viết hoa tất cả dữ liệu)
//        KStream<String, String> transformedStream = stream.mapValues(value -> value.toUpperCase(Locale.ROOT));
//
//        // Ghi dữ liệu vào topic mới
//        transformedStream.to("output_topic");
//
//        kafkaStreams = new KafkaStreams(builder.build(), kafkaStreamsProperties);
//        kafkaStreams.start();
//
//    }


    // Kafka Stream dùng để đếm số từ
    @Autowired
    private WordCountRepository wordCountRepository;
    @Bean
    public KStream<String, String> processStream(StreamsBuilder builder) {
        KStream<String, String> stream = builder.stream("input_topic", Consumed.with(Serdes.String(), Serdes.String()));

        stream
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\s+")))
                .groupBy((key, word) -> word, Grouped.with(Serdes.String(), Serdes.String()))
                .count(Materialized.as("word-count-store"))
                .toStream()
                .peek((word, count) -> saveToDatabase(word, count))
                .to("output_topic", Produced.with(Serdes.String(), Serdes.Long()));

        return stream;
    }
    private void saveToDatabase(String word, Long count) {
        WordCount wordCount = new WordCount(word, count);
        wordCountRepository.save(wordCount);
    }

}
