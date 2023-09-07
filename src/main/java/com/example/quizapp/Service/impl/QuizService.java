package com.example.quizapp.Service.impl;

import com.example.quizapp.DAO.QuestionDAO;
import com.example.quizapp.DAO.QuizDao;
import com.example.quizapp.Model.Question;
import com.example.quizapp.Model.QuestionWrapper;
import com.example.quizapp.Model.Quiz;
import com.example.quizapp.Model.Response;
import com.example.quizapp.Service.QuizServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity<List<Quiz>> getAllQuizes() {
        try{
            List<Quiz> quizList = quizDao.findAll();
            return new ResponseEntity<>(quizList, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        if(quizDao.existsById(id)) {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questionsInQuiz = quiz.get().getQuestionList();
            List<QuestionWrapper> quizQuestions = new ArrayList<>();
            for (Question q : questionsInQuiz) {
                QuestionWrapper clientQuestions = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                quizQuestions.add(clientQuestions);
            }
            return new ResponseEntity<>(quizQuestions,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questionList = quiz.getQuestionList();

        int score = 0;
        int i = 0;
        for(Response r : response){
            if(r.getResponse().equals(questionList.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK );
    }


}
