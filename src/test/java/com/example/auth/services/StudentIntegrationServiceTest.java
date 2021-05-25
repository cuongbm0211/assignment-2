//package com.example.cuongstarterkit.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.example.cuongstarterkit.db.jpa.entities.Student;
//import com.example.cuongstarterkit.db.jpa.repositories.StudentRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//
//@SpringBootTest
//// todo cuongbm can use
////@TestInstance(TestInstance.Lifecycle.PER_CLASS)
////@Transactional
//public class StudentIntegrationServiceTest {
//
//    @Autowired StudentService studentService;
//
//    @Autowired
//    StudentRepository studentRepository;
//
//    @Test
//    void whenCreateStudentSuccess_ThenReturnStudent() {
//        Student cuong = new Student();
//        cuong.setUsername("cuongbm");
//
//        Student student = studentService.createStudent(cuong);
//        Optional<Student> byId = studentRepository.findById(2L);
//        assertEquals("cuongbm", student.getUsername());
//    }
//
//}
