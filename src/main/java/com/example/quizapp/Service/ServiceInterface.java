package com.example.quizapp.Service;

import com.example.quizapp.Model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceInterface {


//    ResponseEntity<List<Question>> getAllQuestions();
    List<Question> getAllQuestions();

    ResponseEntity<List<Question>> getQuestionByCategory(String category);

    ResponseEntity<String> addQuestion(Question question);

    ResponseEntity<String> deleteQuestionById(int id);

    ResponseEntity<String> updateQuestionById(int id, Question question);

}
