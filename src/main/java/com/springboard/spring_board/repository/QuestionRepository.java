package com.springboard.spring_board.repository;

import com.springboard.spring_board.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question,Long> {
    Page<Question> findAll(Pageable pageable);
}
