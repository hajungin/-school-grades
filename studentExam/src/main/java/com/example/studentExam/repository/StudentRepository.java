package com.example.studentExam.repository;

import com.example.studentExam.entity.Exam;
import com.example.studentExam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "select sum(eng) + sum(hist) + sum(kor) + sum(math) from exam  where exam_no= sumNumber", nativeQuery = true)
    List<Exam> sumQuery(@Param("sumNumber")String sumNumber);

    @Query(value = "SELECT (AVG(eng) + AVG(hist) + AVG(kor) + AVG(math)) / 4 FROM exam WHERE exam_no = avgNumber", nativeQuery = true)
    Exam avgQuery(@Param("avgNumber")String avgNumber);
}
