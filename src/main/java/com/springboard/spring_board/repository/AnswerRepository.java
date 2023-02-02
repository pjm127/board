package com.springboard.spring_board.repository;

import com.springboard.spring_board.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
