package com.example.quizapp.Service.impl;

import com.example.quizapp.DAO.QuestionDAO;
import com.example.quizapp.Model.Question;
import com.example.quizapp.Service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Service
public class QuestionService implements ServiceInterface {

    @Autowired
    QuestionDAO questionDao;

//    @Override
//    @Cacheable("All questions") //caches all the questions after a single run but wouldn't work if new data is added
//    public ResponseEntity<List<Question>> getAllQuestions(){
//        try{ //try catch block is used to handle exceptions that could pop up.
//            sleep(1000);
//            return new ResponseEntity(questionDao.findAll(), HttpStatus.OK); //response entity would enable the http status to also be displayed along with the list in this case.
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
//
//    }

    @Override
    @Cacheable("All questions") //caches all the questions after a single run but wouldn't work if new data is added
    public List<Question> getAllQuestions(){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  questionDao.findAll();
    }


    @Override
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.FOUND);
    }

    @Override
    @CacheEvict(value = "All questions", allEntries = true) //this line resets the whole cache whenever a new element is added. Allows for new elements to be cached
    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("data addition successful.", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("data addition unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteQuestionById(int id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("data successfully deleted.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateQuestionById(int id, Question question) {
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
            return new ResponseEntity<>("update successful.", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("update unsuccessful as no records in the specified id is present.", HttpStatus.NOT_FOUND);
        }
    }
}
