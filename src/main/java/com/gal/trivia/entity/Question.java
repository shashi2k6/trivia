package com.gal.trivia.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "question")
public class Question {

    @Id
    private long id;
    private long quiz_id;
    private long question_number;
    private String question;
    private Date created_at;

    public Question() {
    }

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<Answer> answer;

}
