package com.example.studentExam.dto;

//import com.example.studentExam.entity.Exam;
import com.example.studentExam.entity.Exam;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {
    private String exam_no;
    private int kor;
    private int math;
    private int eng;
    private int hist;
    private int sum;
    private double avg;
    private int rank;

    public ExamDto(String examNo, int kor, int math, int eng, int hist) {
        this.exam_no=examNo;
        this.kor=kor;
        this.math=math;
        this.eng=eng;
        this.hist=hist;
    }

    public static ExamDto fromEntity(Exam exam){
        return new ExamDto(
                exam.getExam_no(),
                exam.getKor(),
                exam.getMath(),
                exam.getEng(),
                exam.getHist()
        );
    }
}
