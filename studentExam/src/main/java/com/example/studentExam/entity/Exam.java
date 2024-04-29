package com.example.studentExam.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam {
    @Id
    @Column(length = 6, nullable = false)
    private String exam_no;

    @Column(length = 10)
    private int kor;

    @Column(length = 15)
    private int math;

    @Column(length = 4)
    private int eng;

    @Column(length = 20)
    private int hist;


}
