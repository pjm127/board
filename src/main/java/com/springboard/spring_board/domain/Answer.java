package com.springboard.spring_board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    private String content;
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    public Answer(String content, LocalDateTime createDate, Question question, User writer) {
        this.content = content;
        this.createDate = createDate;
        this.question = question;
        this.writer = writer;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
