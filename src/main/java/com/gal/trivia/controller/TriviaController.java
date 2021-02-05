package com.gal.trivia.controller;

import com.gal.trivia.entity.Question;
import com.gal.trivia.service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TriviaController {

    @Autowired
    private TriviaService triviaService;

    @GetMapping("/questions")
    public List<Question> getRandomQuestions() {
        return triviaService.getRandomQuestions();
    }

    @PostMapping("/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(@RequestBody Question question) {
        triviaService.addQuestion(question);
    }

    @GetMapping("/questions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Question getQuestionById(@PathVariable Long id) {
        return triviaService.getQuestionById(id);
    }

    @DeleteMapping("/questions/{id}")
    public void getRandomQuestions(@PathVariable Long id) {
         triviaService.deleteQuestionById(id);
    }

    @PutMapping("/questions")
    public Question getRandomQuestions(@RequestBody Question question) {
        return triviaService.updateQuestionById(question);
    }
}
