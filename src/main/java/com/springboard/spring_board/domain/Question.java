package com.springboard.spring_board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    public Question(String title, String content,LocalDateTime createDate, User writer) {
        this.title = title;
        this.content= content;
        this.createDate = createDate;
        this.writer = writer;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContent(String content) {
        this.content = content;
    }
}
