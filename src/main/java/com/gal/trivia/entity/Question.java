package com.gal.trivia.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String question;
    private long question_num;
    private String timestamp;

    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn( name = "so_fid", referencedColumnName = "id")    private List<Answer> answers;

}
