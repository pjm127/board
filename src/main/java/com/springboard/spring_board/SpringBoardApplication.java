package com.springboard.spring_board;

import com.springboard.spring_board.domain.Question;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import static java.time.LocalDateTime.now;

@SpringBootApplication
public class SpringBoardApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBoardApplication.class, args);
	}

}
