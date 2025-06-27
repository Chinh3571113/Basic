package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentServiceTest {
    @Test
    public void testGetStudentServiceBean() {
        // Tạo ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Lấy Bean từ ApplicationContext
        StudentService studentService = context.getBean("studentService", StudentService.class);

        // Kiểm tra xem Bean có tồn tại không
        assertNotNull(studentService);
    }
}
