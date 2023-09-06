package com.example.quizapp.Service.impl;

import com.example.quizapp.DAO.QuestionDAO;
import com.example.quizapp.Model.Question;
import com.example.quizapp.Service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService implements ServiceInterface {

    @Autowired
    QuestionDAO questionDao;

    @Override
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{ //try catch block is used to handle exceptions that could pop up.
            return new ResponseEntity(questionDao.findAll(), HttpStatus.OK); //response entity would enable the http status to also be displayed along with the list in this case.
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @Override
    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    @Override
    public String addQuestion(Question question) {
        questionDao.save(question);
        return "data addition successful.";
    }

    @Override
    public String deleteQuestionById(int id) {
        questionDao.deleteById(id);
        return "data successfully deleted.";
    }

    @Override
    public String updateQuestionById(int id, Question question) {
        if(questionDao.existsById(id)){
            Question q1 = questionDao.getReferenceById(id);
            q1.setId(id);
            q1.setQuestionTitle(question.getQuestionTitle());
            q1.setOption1(question.getOption1());
            q1.setOption2(question.getOption2());
            q1.setOption3(question.getOption3());
            q1.setOption4(question.getOption4());
            q1.setRightAnswer(question.getRightAnswer());
            q1.setDifficultyLevel(question.getDifficultyLevel());
            q1.setCategory(question.getCategory());
            questionDao.save(q1);
            return "update successful.";
        }else{
            return "update unsuccessful as no records in the specified id is present.";
        }
    }


}
