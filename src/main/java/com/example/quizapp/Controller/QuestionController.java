package com.example.quizapp.Controller;

import com.example.quizapp.Model.Question;
import com.example.quizapp.Service.impl.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
    @Operation(tags = {"Question operations"},  // the tag will sort out  all the mappings under the same tag under 1 common section in this case question; 2 tags can also be added
            operationId = "geAllQuestions",
            summary = "retrieves all questions from the database",
            description = "just outputs every single question present within the database",
            /*,hidden = true //hides the whole endpoint
            * externalDocs = @ExternalDocumentation(url = "https...", description = "for more details check the link")*/ //provides external documentation
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Question.class), mediaType = MediaType.APPLICATION_JSON_VALUE), description = "question retrieved")}) //provides a given body based on the response code obtained also specifies the type of media obtained.
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}") //the /{category} results in the value of category directly being stored in the variable category which is also assisted by the pathvariable annotation
    @Operation(tags = {"Question operations"},
            operationId = "getQuestionByCategory",
            summary = "Prints questions based on the given category",
            description = "takes in a category as a path variable and uses that to find the corresponding questions thereby ",
            parameters = {@Parameter(name = "category", description ="is the category of questions needed", example = "java")})

    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    @Operation(tags = {"Question operations"},
            operationId = "addQuestions",
            summary = "adds questions on to the database",
            description = "takes in a body and converts it in to an object of question and then adds it on to the database through ORM",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Body of the questions to be added",content = @Content(schema = @Schema(implementation = Question.class))))
            // gives a description to the request body and also specifies the schema associated. By default, its question but can change if needed to.
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    @Operation(tags = {"Question operations"},
                operationId = "deleteQuestions",
                summary = "deletes questions based on the id",
                description = "takes in the id as a path variable and deletes the question with the corresponding id within the database",
                parameters = {@Parameter(name = "id", description = "the path variable", example = "12")})
    public ResponseEntity<String> deleteQuestionById(@PathVariable int id){
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("update/{id}")
    @Operation(tags = {"Question operations"},
                operationId = "updateQuestions",
                summary = "updates questions by id",
                description = "values within the question entry is switched based on the id obtained in the form of a path variable and the question info in the form of a body",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "body of questions to be edited", content = @Content(schema = @Schema(implementation = Question.class))))
    public ResponseEntity<String> updateQuestionById(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }


}
