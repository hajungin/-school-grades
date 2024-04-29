package com.example.studentExam.service;

import com.example.studentExam.dto.ExamDto;
import com.example.studentExam.dto.StudentDto;
import com.example.studentExam.entity.Exam;
import com.example.studentExam.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }
    public void insert(ExamDto dto) {
        Exam exam = Exam.builder()
                .exam_no(dto.getExam_no())
                .kor(dto.getKor())
                .math(dto.getMath())
                .eng(dto.getEng())
                .hist(dto.getHist())
                .build();
        examRepository.save(exam);
    }


    public List<ExamDto> allAvg() {
        List<Exam> examList = examRepository.findAll();
        List<ExamDto> examDtoList = new ArrayList<>();

        for (Exam exam : examList){
            ExamDto examDto = new ExamDto();
            if (exam != null) {
                examDto.setKor(exam.getKor());
                examDto.setEng(exam.getEng());
                examDto.setMath(exam.getMath());
                examDto.setHist(exam.getHist());

            }

            examDto.setKor(exam.getKor());
            examDto.setMath(exam.getMath());
            examDto.setEng(exam.getEng());
            examDto.setHist(exam.getHist());

            examDtoList.add(examDto);
        }

        return examDtoList;

    }

    public ExamDto sum() {
        ExamDto dto = new ExamDto();
        dto.setKor(examRepository.sumKorQuery());
        dto.setMath(examRepository.sumMathQuery());
        dto.setEng(examRepository.sumEngQuery());
        dto.setHist(examRepository.sumHistQuery());
        dto.setSum(examRepository.sumSumQuery());
        dto.setAvg(
                examRepository.sumKorQuery()
                        + examRepository.sumMathQuery()
                        + examRepository.sumEngQuery()
                        + examRepository.sumHistQuery()
        );
        return dto;
    }

    public ExamDto avgKor() {
        ExamDto dto = new ExamDto();
        double temp = Math.round(examRepository.avgKorQuery() * 10) /10.0;
        dto.setAvg(temp);
        return dto;
    }

    public ExamDto avgMath() {
        ExamDto dto = new ExamDto();
        double temp = Math.round(examRepository.avgMathQuery() * 10) /10.0;
        dto.setAvg(temp);
        return dto;
    }

    public ExamDto avgEng() {
        ExamDto dto = new ExamDto();
        double temp = Math.round(examRepository.avgEngQuery() * 10) /10.0;
        dto.setAvg(temp);
        return dto;
    }

    public ExamDto avgHist() {
        ExamDto dto = new ExamDto();
        double temp = Math.round(examRepository.avgHistQuery() * 10) /10.0;
        dto.setAvg(temp);
        return dto;
    }

    public ExamDto avg() {
        List<Exam> examList = examRepository.findAll();
        ExamDto dto = new ExamDto();
        double temp = Math.round(examRepository.sumAvgQuery()/examList.size() * 10) /10.0;
        dto.setAvg(temp);
        return dto;
    }

    public ExamDto avgSum() {
        List<Exam> examList = examRepository.findAll();
        ExamDto dto = new ExamDto();
        double temp = Math.round(examRepository.sumSumQuery()/examList.size() * 10) /10.0;
        dto.setAvg(temp);
        return dto;
    }

}
