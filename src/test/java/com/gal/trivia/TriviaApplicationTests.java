package com.gal.trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gal.trivia.entity.Answer;
import com.gal.trivia.entity.Question;
import com.gal.trivia.repository.QuestionRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TriviaApplicationTests {

    @Value("classpath:trivia.json")
    Resource dataFile;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Question[] questions = objectMapper.readValue(Files.readAllBytes(dataFile.getFile().toPath()), Question[].class);
        Arrays.stream(questions).sequential().forEach(question -> {
            questionRepository.save(question);
        });
    }

    /**
     * Get Random 10 questions from db
     *
     * @throws Exception
     */
    @Test
    public void test_getRandomQuestion() throws Exception {
        mockMvc.perform(get("/api/questions")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    /**
     * @throws Exception
     */
    @Test
    public void test_addQuestionAndAnswer() throws Exception {
        mockMvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuestionAndAnswerObject())))
                .andExpect(status().isCreated());
    }

    /**
     * Delete the question from the DB
     *
     * @throws Exception
     */
    @Test
    public void test_deleteQuestionById() throws Exception {
        mockMvc.perform(delete("/api/questions/5827")).
                andExpect(status().isOk());
    }

    /**
     * Get the question by id.
     *
     * @throws Exception
     */
    @Test
    public void test_getQuestionById() throws Exception {
        mockMvc.perform(get("/api/questions/5998")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5998));
    }

    /**
     * Update the question by id.
     *
     * @throws Exception
     */
    @Test
    public void test_updateQuestionById() throws Exception {
        Question question = new Question();
        question.setId(6024);
        question.setQuestion("The lyric ? dark side of the sun? is sung in what Pink Floyd song?");
        question.setTimestamp("2021-02-04 20:04:25");

        mockMvc.perform(put("/api/questions")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6024));
    }


    /**
     * @return
     */
    private Question getQuestionAndAnswerObject() {
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
        return question;
    }

}
