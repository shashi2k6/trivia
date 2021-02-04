package com.gal.trivia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class TriviaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     *  Get Random 10 questions from db
     */
    @Test
    public void test_getRandom_Question() throws Exception {

        mockMvc.perform(get("/api/questions")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(10)));

    }


}
