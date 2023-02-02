package com.springboard.spring_board.service;

import com.springboard.spring_board.domain.Answer;
import com.springboard.spring_board.domain.Question;
import com.springboard.spring_board.domain.User;
import com.springboard.spring_board.exception.NotFoundException;
import com.springboard.spring_board.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalDateTime.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Transactional
    public void createAnswer(Question question, String content, User writer){
        Answer answer = new Answer(content, now(),question,writer);
        answerRepository.save(answer);
    }


    public Answer getAnswer(Long id){
        return answerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Answer updateAnswer(Long id, String content){
        Answer answer = answerRepository.getById(id);
        answer.updateContent(content);
        return answer;
    }

    @Transactional
    public void deleteAnswer(Answer answer){
        answerRepository.delete(answer);
    }

}
