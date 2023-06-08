package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


class StudentServiceImplTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    void deleteStudentById() {
        Long id = 1L;
        Student student = Student.builder().studentName("Tanya").id(id).marks(124.9).studentRank(1).build();
        assertThat(studentService.deleteStudentById(1L)).isEqualTo("Id " + id + " deleted");
    }
}