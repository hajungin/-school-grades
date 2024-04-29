package com.example.studentExam.service;

import com.example.studentExam.dto.ExamDto;
import com.example.studentExam.dto.StudentDto;
import com.example.studentExam.entity.Exam;
import com.example.studentExam.entity.Student;
import com.example.studentExam.repository.ExamRepository;
import com.example.studentExam.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public StudentService(StudentRepository studentRepository, ExamRepository examRepository1) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository1;
    }

    public List<StudentDto> findAll() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList = studentRepository.findAll()
                .stream()
                .map(x->StudentDto.fromEntity(x))
                .toList();
        return studentDtoList;
    }

    public List<StudentDto> all() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();

        for (Student student : students) {
            Exam exam = examRepository.findById(student.getStudent_no()).orElse(null);
            ExamDto examDto = new ExamDto();
            List<ExamDto> examDtoList = new ArrayList<>();
            if (exam != null) {
                examDto.setExam_no(exam.getExam_no());
                examDto.setKor(exam.getKor());
                examDto.setEng(exam.getEng());
                examDto.setMath(exam.getMath());
                examDto.setHist(exam.getHist());
                int sum = exam.getKor() + exam.getEng() + exam.getMath() + exam.getHist();
                double avg = sum / 4.0; // 과목 수로 나누어서 평균을 계산
                examDto.setSum(sum);
                examDto.setAvg(avg);


                int rank = 1;
                for (Student otherStudent : students) {
                    Exam otherExam = examRepository.findById(otherStudent.getStudent_no()).orElse(null);
                    if (otherExam != null) {
                        int otherSum = otherExam.getKor() + otherExam.getEng() + otherExam.getMath() + otherExam.getHist();
                        double otherAvg = otherSum / 4.0;
                        if (avg < otherAvg) {
                            rank++;
                        }
                    }
                }
                examDto.setRank(rank);

//                do {
//                    int rank = 1;
//                    for (int i = 0; i < students.size(); i++) {
//                        double min = studentDtoList.get(i).getExamDto().getAvg();
//                        for (int k = 0; k < students.size(); k++){
//                            if (min > studentDtoList.get(i+1).getExamDto().getAvg()){
//                                studentDtoList.get(i).getExamDto().setRank(rank);
//                                ++rank;
//                            } else if (min < studentDtoList.get(i+1).getExamDto().getAvg()) {
//                                studentDtoList.get(i);
//                            }
//                        }
//                     }
//                }while (false);

//                examDto.setRank(rank);


            }
//            int rank = 1;
//            examDtoList.stream()
//                    .sorted(Comparator.comparingDouble(ExamDto :: getAvg).reversed())
//                    .forEach(System.out::println);
////                역순정렬
//
//
//            for (int i = 0; i < examDtoList.size(); i++) {
//                if (examDtoList.get(i).getAvg() < examDtoList.get(i + 1).getAvg()) {
//                    rank = i + 1;
//                }
//                examDtoList.get(i).setRank(rank);
//            }


            StudentDto studentDto = new StudentDto();
            studentDto.setStudent_no(student.getStudent_no());
            studentDto.setName(student.getName());
            studentDto.setPhone(student.getPhone());
            studentDto.setGender(student.getGender());
            studentDto.setAddress(student.getAddress());
            studentDto.setExamDto(examDto);

            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }
}
