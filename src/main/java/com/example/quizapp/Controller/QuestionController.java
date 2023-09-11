package com.example.quizapp.Controller;

import com.example.quizapp.Model.Question;
import com.example.quizapp.Service.impl.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@Api
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    @Operation(tags = {"Question operations"}) // the tag will sort out  all the mappings under the same tag under 1 common section in this case question; 2 tags can also be added
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}") //the /{category} results in the value of category directly being stored in the variable category which is also assisted by the pathvariable annotation
    @Operation(tags = {"Question operations"})
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    @Operation(tags = {"Question operations"})
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    @Operation(tags = {"Question operations"})
    public ResponseEntity<String> deleteQuestionById(@PathVariable int id){
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("update/{id}")
    @Operation(tags = {"Question operations"})
    public ResponseEntity<String> updateQuestionById(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }


}
