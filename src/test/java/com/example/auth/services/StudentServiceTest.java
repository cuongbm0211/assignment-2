//package com.example.auth.services;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.example.auth.db.jpa.entities.Student;
//import com.example.auth.db.jpa.repositories.StudentRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class StudentServiceTest {
//
//    @Mock
//    StudentRepository studentRepository;
//
//    @InjectMocks
//    StudentService studentService = new StudentServiceImpl();
//
//    @BeforeEach
//    void setup() {
//        when(studentRepository.save(any())).thenReturn(any());
//    }
//
////    @Test
////    void whenCreateStudentWithEmptyName_ThenThrowException() {
////        Student emptyName = new Student();
////
////        assertThatThrownBy(() -> studentService.createStudent(emptyName))
////            .isInstanceOf(AppException.class)
////            .extracting(ex -> ((AppException) ex).getCode())
////            .isEqualTo("0001");
////    }
//
//    @Test
//    void givenValidStudent_WhenCreate_ThenRepoCallOneTime() {
//        Student cuong = new Student();
//        cuong.setUsername("cuongbm");
//
//        studentService.createStudent(cuong);
//        verify(studentRepository, times(1)).save(any());
//    }
//
//}