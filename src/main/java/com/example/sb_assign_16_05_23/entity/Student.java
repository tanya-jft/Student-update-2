package com.example.sb_assign_16_05_23.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Audited
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    //here we are creating an entity "student" which will create a table in the DB by the same name

    //It will automatically create columns in the table - id, student_name, marks

    //lombok is taking care of the constructors/getters/setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "marks")
    private Double marks;

    @NotAudited
    @Column(name = "student_rank")
    private Integer studentRank;

}
