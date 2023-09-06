package com.example.quizapp.Service;

import com.example.quizapp.Model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceInterface {


    ResponseEntity<List<Question>> getAllQuestions();


    List<Question> getQuestionByCategory(String category);

    String addQuestion(Question question);

    String deleteQuestionById(int id);

    String updateQuestionById(int id, Question question);

}
