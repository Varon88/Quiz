package com.example.quizapp.Service;

import com.example.quizapp.Model.QuestionWrapper;
import com.example.quizapp.Model.Quiz;
import com.example.quizapp.Model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizServiceInterface {

    ResponseEntity<String> createQuiz(String category, int id, String titleJ);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

    ResponseEntity<List<Quiz>> getAllQuizes();

    ResponseEntity<Integer> calculateResult(Integer id, List<Response> response);
}
