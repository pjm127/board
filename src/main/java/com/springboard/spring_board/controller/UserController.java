package com.springboard.spring_board.controller;

import com.springboard.spring_board.form.UserCreateForm;
import com.springboard.spring_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("userCreateForm",new UserCreateForm());
        return "sign_form";
    }

    @PostMapping("/join")
    public String join(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "sign_form";
        }

        try {
            userService.createUser(userCreateForm.getUsername(),
                    userCreateForm.getPassword1(), userCreateForm.getEmail());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("joinFailed", "이미 등록된 사용자입니다.");
            return "sign_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("joinFailed", e.getMessage());
            return "sign_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

}
