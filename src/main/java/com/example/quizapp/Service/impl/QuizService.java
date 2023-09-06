package com.example.quizapp.Service.impl;

import com.example.quizapp.DAO.QuestionDAO;
import com.example.quizapp.DAO.QuizDao;
import com.example.quizapp.Model.Question;
import com.example.quizapp.Model.Quiz;
import com.example.quizapp.Service.QuizServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizService implements QuizServiceInterface {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDAO questionDAO;

    @Override
    public ResponseEntity<String> createQuiz(String category, int num, String title) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, num);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("quiz successfully created", HttpStatus.CREATED);

    }
}
