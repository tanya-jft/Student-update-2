package com.example.sb_assign_16_05_23.service;
<<<<<<< HEAD
=======

>>>>>>> feature-pradeep
import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import java.util.List;

public interface StudentService {
<<<<<<< HEAD
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(StudentDTO studentDTO);
=======

    StudentDTO updateStudent(Student studentData);

    ResponseDTO getAllStudents();
>>>>>>> feature-pradeep
}
