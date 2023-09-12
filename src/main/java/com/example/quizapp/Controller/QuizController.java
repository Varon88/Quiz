package com.example.quizapp.Controller;


import com.example.quizapp.Model.QuestionWrapper;
import com.example.quizapp.Model.Quiz;
import com.example.quizapp.Model.Response;
import com.example.quizapp.Service.impl.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    @Operation(tags = {"Quiz operations"}, // the tag will sort out all the mappings under the same tag under 1 common section in this case quiz
                operationId = "createQuiz",
                summary = "creates a quiz by category ",
                description = "creates a quiz by taking in factors such as category, number of questions and the desired quiz title as parameters " )
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);

    }

    @GetMapping("getall")
    @Operation(tags = {"Quiz operations"},
            operationId = "getAllQuizzes",
            summary = "prints all the available quizzes",
            description = "no parameters would be taken in, main fucntion would be to retrieve all elements withing the database")
    public ResponseEntity<List<Quiz>> getAllQuizes(){
        return quizService.getAllQuizes();
    }

    @GetMapping("get/{id}")
    @Operation(tags = {"Quiz operations"},
               operationId = "quizRelatedQuestions",
               summary = "gives out the questions in the quiz based on id entered",
               description = "would take in the id as a pathvariable and used the id to retrieve questions from the corresponding quiz")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    @Operation(tags = {"Quiz operations"},
            operationId = "quizResult",
            summary = "outputs results after the submission of answers",
            description = "answers entered in the form of a raw body would be analysed and results would be output")
    public ResponseEntity<Integer> submitAnswers(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }
}


























