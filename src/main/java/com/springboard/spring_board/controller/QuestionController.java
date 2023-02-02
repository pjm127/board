package com.springboard.spring_board.controller;

import com.springboard.spring_board.domain.Question;
import com.springboard.spring_board.domain.User;
import com.springboard.spring_board.form.AnswerForm;
import com.springboard.spring_board.form.QuestionForm;
import com.springboard.spring_board.service.QuestionService;
import com.springboard.spring_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging",paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id,Model model){
        Question question = questionService.getQuestion(id);
        model.addAttribute("question",question);
        model.addAttribute("answerForm",new AnswerForm());
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(Model model){
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm form, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        User writer = userService.getUser(principal.getName());
        questionService.createQuestion(form.getTitle(), form.getContent(), writer);
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String questionUpdate(Model model,@PathVariable Long id, Principal principal) {
        Question question = questionService.getQuestion(id);
        if(!question.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        QuestionForm questionForm = new QuestionForm();
        questionForm.setTitle(question.getTitle());
        questionForm.setContent(question.getContent());

        model.addAttribute("questionForm",questionForm);
        return "question_update";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String questionUpdate(@PathVariable Long id, @Valid @ModelAttribute QuestionForm questionForm,Principal principal,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionService.updateQuestion(id, questionForm.getTitle(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(@PathVariable Long id,Principal principal) {
        Question question = questionService.getQuestion(id);
        if (!question.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        questionService.deleteQuestion(question);
        return "redirect:/";
    }
}
