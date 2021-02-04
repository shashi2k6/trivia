package com.gal.trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gal.trivia.entity.Question;
import com.gal.trivia.entity.QuestionRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.util.Arrays;

@SpringBootApplication
public class TriviaApplication implements CommandLineRunner {

	@Value("classpath:trivia.json")
	Resource dataFile;

	@Autowired
	private QuestionRepositiory questionRepositiory;

	public static void main(String[] args) {
		SpringApplication.run(TriviaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		Question[] questions = objectMapper.readValue(Files.readAllBytes(dataFile.getFile().toPath()), Question[].class);

		Arrays.stream(questions).sequential().forEach(question -> {
			questionRepositiory.save(question);
		});


		System.out.println("done");
	}
}
