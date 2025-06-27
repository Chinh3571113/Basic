//package com.example.basic.student;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//
//@Configuration
//public class StudentConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository) {
//        return args -> {
//            Student student = new Student(1L, "Tohm", "Tohm@gmail.com", 21);
//            repository.save(student);
//        };
//    }
//}
