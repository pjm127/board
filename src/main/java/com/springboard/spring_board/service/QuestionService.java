package com.springboard.spring_board.service;

import com.springboard.spring_board.domain.Question;
import com.springboard.spring_board.domain.User;
import com.springboard.spring_board.exception.NotFoundException;
import com.springboard.spring_board.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
        return questionRepository.findAll(pageable);
    }
    public Question getQuestion(Long id){
        return questionRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    @Transactional
    public Question createQuestion(String title, String content, User writer){
        Question question = new Question(title,content,now(),writer);
        return questionRepository.save(question);
    }

    @Transactional
    public Question updateQuestion(Long id, String title, String content) {
        Question question = questionRepository.getById(id);
        question.updateTitle(title);
        question.updateContent(content);
        return question;
    }

    @Transactional
    public void deleteQuestion(Question question){
        questionRepository.delete(question);
    }
}


