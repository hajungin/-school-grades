package com.example.studentExam.dto;

import com.example.studentExam.constant.Gender;
import com.example.studentExam.entity.Student;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String student_no;
    private String name;
    private String phone;
    private Gender gender;
    private String address;
    private ExamDto examDto;

    public static StudentDto fromEntity(Student student){
        return new StudentDto(
                student.getStudent_no(),
                student.getName(),
                student.getPhone(),
                student.getGender(),
                student.getAddress(),
                new ExamDto()
        );
    }
}
