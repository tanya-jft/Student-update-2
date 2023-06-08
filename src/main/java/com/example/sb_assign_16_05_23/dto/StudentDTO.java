package com.example.sb_assign_16_05_23.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id"}, allowGetters = true) // ignore the property while saving, but allow getter for those properties
public class StudentDTO {
    //the fields of DTO should match the entity's field
    Long id;

    @NotBlank(message = "Name can not be blank")
    String studentName;

    @NotNull(message = "Marks are required")
    @Range(min = 0, max = 600, message = "Marks must be between 0 to 600")
    Double marks;

    Integer studentRank = 0;
}
