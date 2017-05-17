package com.quizzer.resources;

import java.util.List;

/**
 * Created by Elas PC on 2017-05-17.
 */

public class Quiz {
    int id;
    String name;
    List<Question> questions;
    int numberOfQuestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Quiz(int id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.numberOfQuestions = questions.size();
    }
}
