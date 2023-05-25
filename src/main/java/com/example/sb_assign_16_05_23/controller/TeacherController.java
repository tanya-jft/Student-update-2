package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.service.StudentService;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    //teacher controller to create endpoint /api/teachers

    @Autowired
    TeacherService teacherService;

    @GetMapping
    private ResponseDTO<TeacherDTO> getTeachers() {

        //returns list of teachers from TeacherService
        return teacherService.getAllTeachers();
    }

    @PostMapping
    private ResponseDTO<TeacherDTO> setTeachers(@RequestBody TeacherDTO teacherData) {
        return teacherService.setTeachers(teacherData);
    }

    @PostMapping("/list")
    private ResponseDTO<TeacherDTO> setAll(@RequestBody List<TeacherDTO> teacherData) {

        return teacherService.setAll(teacherData);
    }
}