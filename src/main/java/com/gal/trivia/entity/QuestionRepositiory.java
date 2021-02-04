package com.gal.trivia.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepositiory extends JpaRepository<Question,Long> {

}
