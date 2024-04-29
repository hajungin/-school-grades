package com.example.studentExam.repository;

import com.example.studentExam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, String> {
    @Query(value = "select sum(kor) from exam", nativeQuery = true)
    int sumKorQuery();
    @Query(value = "select sum(eng) from exam", nativeQuery = true)
    int sumEngQuery();
    @Query(value = "select sum(math) from exam", nativeQuery = true)
    int sumMathQuery();
    @Query(value = "select sum(hist) from exam", nativeQuery = true)
    int sumHistQuery();
    @Query(value = "SELECT SUM(kor + math + eng + hist)  FROM exam", nativeQuery = true)
    int sumSumQuery();
    @Query(value = "SELECT AVG(total_score) AS average_total_score FROM (SELECT kor + math + eng + hist AS total_score FROM exam) AS total_scores", nativeQuery = true)
    double sumAvgQuery();


    @Query(value = "SELECT AVG(kor) FROM exam", nativeQuery = true)
    double avgKorQuery();
    @Query(value = "SELECT AVG(eng) FROM exam", nativeQuery = true)
    double avgEngQuery();
    @Query(value = "SELECT AVG(math) FROM exam", nativeQuery = true)
    double avgMathQuery();
    @Query(value = "SELECT AVG(hist) FROM exam", nativeQuery = true)
    double avgHistQuery();

}
