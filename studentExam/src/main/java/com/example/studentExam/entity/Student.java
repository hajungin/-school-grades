package com.example.studentExam.entity;

import com.example.studentExam.constant.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @Column(length = 6, nullable = false)
    private String student_no;

    @Column(length = 10)
    private String name;

    @Column(length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 20)
    private String address;
}
