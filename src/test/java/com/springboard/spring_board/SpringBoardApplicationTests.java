package com.springboard.spring_board;

import com.springboard.spring_board.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoardApplicationTests {

	@Autowired
	private QuestionService questionService;
	@Test
	void test() {
		for (int i= 0; i<=100; i++){
			String title = String.format("테스트 :[%03d]", i);
			String content = "테스트 내용";
			this.questionService.createQuestion(title, content,null);
		}
	}

}
