package com.gal.trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gal.trivia.entity.Answer;
import com.gal.trivia.entity.Question;
import com.gal.trivia.entity.QuestionRepositiory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TriviaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:trivia.json")
    Resource dataFile;

    @Autowired
    private QuestionRepositiory questionRepositiory;

    @BeforeEach
    public void init() throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Question[] questions = objectMapper.readValue(Files.readAllBytes(dataFile.getFile().toPath()), Question[].class);

		Arrays.stream(questions).sequential().forEach(question -> {
			questionRepositiory.save(question);
		});


		System.out.println("done");
    }

    /**
     * Get Random 10 questions from db
     *
     * @throws Exception
     */
    @Test
    public void test_getRandom_Question() throws Exception {
        mockMvc.perform(get("/api/questions")).
                andExpect(status().isOk());
               // .andExpect(jsonPath("$", hasSize(10)));
    }

    /**
     * @throws Exception
     */
    @Test
    public void test_addQuestion() throws Exception {

        Question question = new Question();
        question.setQuestion("What is the capital of US ?");

        Answer answer = new Answer();
        answer.setText("Newyork");
        answer.setChoice("A");

        Answer answer1 = new Answer();
        answer1.setText("Washington");
        answer1.setChoice("B");

        question.setTimestamp("2018-02-02 20:04:25");
        question.setAnswers(Arrays.asList(answer));

        mockMvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isCreated());
    }
}
