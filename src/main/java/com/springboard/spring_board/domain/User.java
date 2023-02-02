package com.springboard.spring_board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    private String role;

    @OneToMany(mappedBy = "writer")
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "writer" )
    private List<Answer> answerList = new ArrayList<>();


   public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     *
     * 로그인 안한 유저
     */
    public User(String username) {
        this.username = username;
    }
}
