package com.example.quizapp.Controller;

import com.example.quizapp.Model.Question;
import com.example.quizapp.Service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}") //the /{category} results in the value of category directly being stored in the variable category which is also assisted by the pathvariable annotation
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable int id){
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }


}
