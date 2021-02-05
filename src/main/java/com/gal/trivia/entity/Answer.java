package com.gal.trivia.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="answer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue
    private long id;

    private String choice;
    private String text;
    private boolean correct;

}
