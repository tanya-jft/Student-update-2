package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    public SubjectServiceImpl(TeacherRepository teacherRepository, SubjectRepository subjectRepository , ModelMapper modelMapper) {
        this.subjectRepository=subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SubjectDTO> getAllSubjects(String teacherName){
        List<SubjectDTO> subjectDTOList=subjectRepository.findByTeacherName(teacherName).stream()
                .map(subject -> modelMapper.map(subject,SubjectDTO.class))
                .toList();
        return subjectDTOList;
    }

    @Override
    public String deleteSubjectById(Long id) {
        Optional<Subject> isSubjectExist = subjectRepository.findById(id);
        if(isSubjectExist.isPresent()) subjectRepository.delete(isSubjectExist.get());
        String msg = (!isSubjectExist.isPresent()) ? ("Id " + id + " does not exist.") : ("Id " + id + " deleted");

        return msg;
    }
}
