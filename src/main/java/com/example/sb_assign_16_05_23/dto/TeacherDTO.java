package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    Long id;

    @NotBlank(message = "Teacher can not be blank")
    String name;

    @Valid
    @NotEmpty(message = "Subject list can not be empty")
    List<SubjectDTO> subjects;
}