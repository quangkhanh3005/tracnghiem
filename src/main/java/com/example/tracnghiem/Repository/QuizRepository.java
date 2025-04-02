package com.example.tracnghiem.Repository;

import com.example.tracnghiem.Model.Quiz;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

    boolean existsByCode(String code);
    Optional<Quiz> findById(int id);
    Optional<Quiz> findByCode(String code);
    List<Quiz> findAllByTopic_Id(int topicId);

    int id(int id);
}
