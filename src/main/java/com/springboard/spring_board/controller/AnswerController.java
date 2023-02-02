package com.springboard.spring_board.controller;

import com.springboard.spring_board.domain.Answer;
import com.springboard.spring_board.domain.Question;
import com.springboard.spring_board.domain.User;
import com.springboard.spring_board.form.AnswerForm;
import com.springboard.spring_board.service.AnswerService;
import com.springboard.spring_board.service.QuestionService;
import com.springboard.spring_board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
@Slf4j
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable Long id, Model model, @Valid AnswerForm form,
                               BindingResult bindingResult, Principal principal) {
        Question question = questionService.getQuestion(id);
        User writer = userService.getUser(principal.getName());


        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.createAnswer(question, form.getContent(),writer);
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String answerUpdate(Model model,@PathVariable Long id,Principal principal) {
        Answer answer = answerService.getAnswer(id);
        if (!answer.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        AnswerForm answerForm = new AnswerForm();
        answerForm.setContent(answer.getContent());
        model.addAttribute("answerForm",answerForm);
        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String answerUpdate(@PathVariable Long id, @ModelAttribute AnswerForm answerForm){
        answerService.updateAnswer(id,answerForm.getContent());
        Answer answer = answerService.getAnswer(id);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(@PathVariable Long id,Principal principal) {
        Answer answer = answerService.getAnswer(id);
        if (!answer.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerService.deleteAnswer(answer);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

}
