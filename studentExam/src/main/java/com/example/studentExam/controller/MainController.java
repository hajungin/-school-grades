package com.example.studentExam.controller;

import com.example.studentExam.dto.ExamDto;
import com.example.studentExam.dto.StudentDto;
import com.example.studentExam.entity.Exam;
import com.example.studentExam.repository.ExamRepository;
import com.example.studentExam.service.ExamService;
import com.example.studentExam.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/school")
public class MainController {
    private final StudentService studentService;
    private final ExamService examService;

    public MainController(StudentService studentService, ExamService examService) {
        this.studentService = studentService;
        this.examService = examService;
    }

    @GetMapping("main")
    public String mainView(Model model){
        return "school/show_all";
    }
    @GetMapping("show")
    public String showView(Model model){
        List<StudentDto> studentDtoList = studentService.findAll();
        model.addAttribute("student", studentDtoList);
        log.info(studentDtoList.toString());
        return "school/show";
    }

    @GetMapping("insert")
    public String insertView(Model model){
        model.addAttribute("exam", new ExamDto());
        List<StudentDto> studentDtoList = studentService.findAll();
        model.addAttribute("studentDto", studentDtoList);
        return "school/insert";
    }
    @PostMapping("insert")
    public String insert(@ModelAttribute("exam")ExamDto dto){
        examService.insert(dto);
        return "redirect:/school/main";
    }

    @GetMapping("grade")
    public String gradeView(Model model){
        List<StudentDto> studentDto = studentService.all();
        log.info(studentDto.toString());
        model.addAttribute("student",studentDto);

    // 총 합계 계산
        ExamDto examDto = examService.sum();
        model.addAttribute("exam0",examDto);
    // 총 평균 계산
        ExamDto avgKor = examService.avgKor();
        ExamDto avgMath = examService.avgMath();
        ExamDto avgEng = examService.avgEng();
        ExamDto avgHist = examService.avgHist();
        ExamDto avgSum = examService.avgSum();
        ExamDto avg = examService.avg();
        model.addAttribute("avgKor",avgKor);
        model.addAttribute("avgMath",avgMath);
        model.addAttribute("avgEng",avgEng);
        model.addAttribute("avgHist",avgHist);
        model.addAttribute("avgSum",avgSum);
        model.addAttribute("avg",avg);


//서비스에서 이중포문을 돌려 리스트로 값을 넣어서 석차를 하나씩 올려나간뒤 출력해준다
//        List<ExamDto> examDtoList = examService.rank();
//        model.addAttribute("examDtoList", examDtoList);


        return "school/grade";
    }
}

