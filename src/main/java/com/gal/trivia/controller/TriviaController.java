package com.gal.trivia.controller;

import com.gal.trivia.entity.Question;
import com.gal.trivia.service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TriviaController {

    @Autowired
    private TriviaService triviaService;

    @GetMapping("/api/questions")
    public List<Question> getRandomQuestions(){
        return triviaService.getRandomQuestions();
    }
}
