package com.example.tracnghiem.Repository;

import com.example.tracnghiem.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Optional<Topic> findByName(String name);
}
