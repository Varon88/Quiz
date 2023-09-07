package com.example.quizapp.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


//TODO had to create tables manually for some reason depite having the update function
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToMany
    private List<Question> questionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
