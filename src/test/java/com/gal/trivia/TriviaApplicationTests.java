package com.gal.trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gal.trivia.entity.Answer;
import com.gal.trivia.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TriviaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Get Random 10 questions from db
     *
     * @throws Exception
     */
    @Test
    public void test_getRandom_Question() throws Exception {
        mockMvc.perform(get("/api/questions")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    /**
     * @throws Exception
     */
    @Test
    public void test_addQuestion() throws Exception {

        Question question = new Question();
        question.setId(15001);
        question.setQuiz_id(1);
        question.setQuestion("What is the capital of US ?");

        Answer answer = new Answer();
        answer.setId(25000);
        answer.setQuestion_id(15001);
        answer.setText("Newyork");
        answer.setChoice("A");

        Answer answer1 = new Answer();
        answer1.setId(25001);
        answer1.setQuestion_id(15001);
        answer1.setText("Washington");
        answer1.setChoice("B");

        question.setCreated_at(new Date());
        question.setAnswer(Arrays.asList(answer, answer1));

        mockMvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isCreated());
    }
}
