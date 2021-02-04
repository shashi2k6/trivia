package com.gal.trivia.service;

import com.gal.trivia.entity.Question;
import com.gal.trivia.entity.QuestionRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TriviaService {

    @Autowired
    private QuestionRepositiory questionRepositiory;

    public List<Question> getRandomQuestions() {
        List<Question> questionList = questionRepositiory.findAll();
        IntStream intStream = new Random().ints(10, 0, questionList.size());
        List<Integer> randomList = intStream.map(i -> Integer.valueOf(i)).boxed().collect(Collectors.toList());
        List<Question> randomQuestions = new ArrayList<>();

        randomList.stream().forEach(no -> {
            Question question = questionList.get(no);
            randomQuestions.add(question);
        });
        return randomQuestions;
    }

    public void addQuestion(Question question) {

        Question question1 = questionRepositiory.save(question);
        System.out.println(question1.getId());
    }
}
