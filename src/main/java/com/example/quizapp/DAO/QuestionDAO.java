package com.example.quizapp.DAO;

import com.example.quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{ //data jpa handles all database functions such as retrieval of data

    List<Question> findByCategory(String Category);

}
