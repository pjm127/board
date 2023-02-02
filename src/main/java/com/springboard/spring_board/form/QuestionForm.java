package com.springboard.spring_board.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class QuestionForm {
    @NotEmpty(message="제목을 입력하세요")
    private String title;

    @NotEmpty(message="내용을 입력하세요")
    private String content;
}
