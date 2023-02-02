package com.springboard.spring_board.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty(message = "사용자ID을 입력하세요")
    private String username;

    @NotEmpty(message = "비밀번호을 입력하세요")
    private String password1;

    @NotEmpty(message = "비밀번호을 입력하세요")
    private String password2;

    @NotEmpty(message = "이메일을 입력하세요")
    @Email
    private String email;
}
