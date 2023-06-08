package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.util.Constants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    ModelMapper mapper;


    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getAllStudents_no_condtions_test() {
        Student student1 = Student.builder()
                .studentName("Tanya").studentRank(3).marks(459.90).id(937L).build();

        Student student2 = Student.builder()
                .studentName("Manushi").studentRank(1).marks(600.0).id(2875L).build();

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        assertThat(studentService.getAllStudents()).hasSize(2);
        verify(studentRepository, times(1)).findAll();

    }

    //    @DisplayName("Empty List - getAllConditions")
    @Test
    void getAllStudents_empty_istt_test(){
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> studentService.getAllStudents())
                .isInstanceOf(NotFoundException.class)
                .hasMessage(Constants.EMPTY_LIST);
    }

    // highestRankTest()
/*    @Test
    void getHighestRank_no_conditions_test(){
        Student student1 = Student.builder()
                .studentName("Tanya").studentRank(3).marks(459.90).id(937L).build();

        Student student2 = Student.builder()
                .studentName("Manushi").studentRank(1).marks(600.0).id(2875L).build();

    }*/


    /* calculateRank()
    * studentRepository.findAllByMarksLessThanEqualOrderByMarksDesc(highest)
    * studentRepository.findFirstByOrderByMarks()
    */
/*    void calculateRank_basic_test(){
        Student student1 = Student.builder()
                .studentName("Tanya").studentRank(2).marks(459.90).id(937L).build();

        Student student2 = Student.builder()
                .studentName("Manushi").studentRank(1).marks(598.0).id(2875L).build();

        double highest = 600;

        when(studentRepository.findAllByMarksLessThanEqualOrderByMarksDesc(highest)).thenReturn(List.of(student2, student1));
        when(studentRepository.findFirstByOrderByMarks()).thenReturn(student1);


    }*/

}