package com.example.service;

import com.example.service.dao.EmployeeDAO;

public class EmployeeService {
    private EmployeeDAO employeeDAO;

    // Setter Injection
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void showEmployeeInfo() {
        employeeDAO.getEmployeeInfo();
    }
}
