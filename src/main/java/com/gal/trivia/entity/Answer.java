package com.gal.trivia.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="answer")
public class Answer {

    @Id
    private long id;

    private long question_id;
    private String choice;
    private String text;
    private boolean correct;

    public Answer() {
    }

    @ManyToOne()
    @JoinColumn(name="quiz_id")
    private Question question;

}
