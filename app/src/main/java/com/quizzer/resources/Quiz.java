package com.quizzer.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elas PC on 2017-05-17.
 */

public class Quiz {
    int id;
    String name;
    List<Question> questions;
    
    public ArrayList<String> getQuestionsNames() {
        ArrayList<String> list = new ArrayList<>();
        for (Question question: questions) {
            list.add(question.getName());
        }
        return list;
    }

    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<Question>();
    }

    public void AddQuestion(Question question) {
        questions.add(question);
    }

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
        return questions.size();
    }


    public Quiz(int id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }
}
