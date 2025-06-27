package com.example;

import com.example.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // Tạo ApplicationContext từ file XML
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Lấy Bean từ ApplicationContext
        EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);

        // Gọi phương thức để kiểm tra Bean hoạt động
        employeeService.showEmployeeInfo();
    }
}
