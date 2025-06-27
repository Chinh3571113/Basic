package com.example.basic.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// Service: chứa các bussiness login code
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private static  final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new IllegalStateException("Student does not exist");
        }
        return students;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }
}
