package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import com.example.sb_assign_16_05_23.util.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper mapper;


    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) throw new NotFoundException(Constants.EMPTY_LIST);

        return students.stream().map(student -> mapper.map(student, StudentDTO.class)).toList();
    }


    @Override // register all students
    public List<StudentDTO> registerStudentList(List<StudentDTO> studentDtos) {
        List<Student> newStudent = studentDtos.stream().map(s -> mapper.map(s, Student.class)).toList();
        List<Student> students = calculateRank(newStudent);
        List<StudentDTO> newStudentDtos = new ArrayList<>();// return the dtos List with allocated id and rank

        students.forEach(s -> {
            if (s.getId() == null) { // if id=null then it is new student obj which is not registered yet
                studentRepository.save(s);
                newStudentDtos.add(mapper.map(s, StudentDTO.class));
            } else studentRepository.save(s);
        });

        return newStudentDtos;
    }

    // recalculate rank
    @Override
    public List<Student> calculateRank(List<Student> newStudent) {
        double highest = newStudent.stream().max((s1, s2) -> s1.getMarks().compareTo(s2.getMarks())).get().getMarks();

        // list of students having marks smaller than given dto list(highest marks)
        List<Student> students = studentRepository.findAllByMarksLessThanEqualOrderByMarksDesc(highest);

        // if students list have no marks smaller than dto's highest marks
        Student filterStudent = students.isEmpty() ? studentRepository.findFirstByOrderByMarks() : null;

        int ifRank = filterStudent != null ? filterStudent.getStudentRank() + 1 : 1; // if both list is empty than rank=1
        int j = (students.isEmpty() ? ifRank : students.get(0).getStudentRank());

        students.addAll(newStudent);
        students.sort((s1, s2) -> s2.getMarks().compareTo(s1.getMarks()));

        Map<Double, Integer> mappingList = new HashMap<>();

        for (Student student : students) { // set the map
            double marks = student.getMarks();
            if (!mappingList.containsKey(marks)) mappingList.put(marks, j++);
            else mappingList.put(marks, mappingList.get(marks));
        }

        students.forEach(student -> { // set ranks
            double marks = student.getMarks();
            if (mappingList.containsKey(marks)) student.setStudentRank(mappingList.get(marks));
        });

        return students;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {

        Student existingStudent = studentRepository.findById(studentDTO.getId()).orElseThrow(() -> new NotFoundException("Student not found with id " + studentDTO.getId()));

        mapper.map(studentDTO, existingStudent);

        calculateRank(studentRepository.findAll());

        studentRepository.save(existingStudent);
        TypeToken<StudentDTO> token = new TypeToken<>() {
        };
        // Casting student class to StudentDTO
        return mapper.map(existingStudent, token.getType());

    }

}
