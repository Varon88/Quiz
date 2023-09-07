package com.example.quizapp.Service;

import com.example.quizapp.Model.QuestionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizServiceInterface {

    ResponseEntity<String> createQuiz(String category, int id, String titleJ);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);
}
