//package com.example.basic.services;
//
//import jakarta.annotation.PostConstruct;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.KStream;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StreamService {
//    @Autowired
//    private StreamsBuilder streamsBuilder;
//
//    @PostConstruct
//    public void processStream() {
//        KStream<String, String> sourceStream  = streamsBuilder.stream("replication-topic");
//        // Lọc dữ liệu: chỉ giữ lại chuỗi không chứa ký tự đặc biệt
//        KStream<String, String> filteredStream = sourceStream.filter((key, value) -> value.matches("[a-zA-Z0-9 ]*"));
//
//        // Gửi dữ liệu đã lọc vào topic khác
//        filteredStream.to("filtered-topic");
//
//        // Xây dựng Kafka Streams
//        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), streamsBuilder.build().propertie);
//        streams.start();
//    }
//}
