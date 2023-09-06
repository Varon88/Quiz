package com.example.quizapp.Service;

import org.springframework.http.ResponseEntity;

public interface QuizServiceInterface {

    ResponseEntity<String> createQuiz(String category, int id, String titleJ);
}
