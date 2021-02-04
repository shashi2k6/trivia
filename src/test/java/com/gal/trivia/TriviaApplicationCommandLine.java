package com.gal.trivia;

import com.gal.trivia.entity.Answer;
import com.gal.trivia.entity.Question;
import com.gal.trivia.entity.QuestionRepository;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

//@SpringBootApplication
public class TriviaApplicationCommandLine implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TriviaApplicationCommandLine.class, args);
	}

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void run(String... args) throws Exception {

		Question question = new Question();
		question.setId(15001);
		question.setQuiz_id(1);
		question.setQuestion_number(15001);
		question.setQuestion("What is the capital of US ?");
		question.setCreated_at(new Date());

		Answer answer = new Answer();
		answer.setId(25000);
		answer.setQuestion_id(15001);
		answer.setChoice("A");
		answer.setText("NewYork");

		Answer answer1 = new Answer();
		answer1.setId(25001);
		answer1.setQuestion_id(15001);
		answer1.setChoice("A");
		answer1.setText("Chicago");


		question.setAnswer(Arrays.asList(answer,answer1));

		this.questionRepository.save(question);

	}
}
