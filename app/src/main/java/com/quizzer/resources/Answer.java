package com.quizzer.resources;

/**
 * Created by Elas PC on 2017-05-17.
 */

public class Answer {
    String question;
    int id;

    public Answer(String question, int id) {
        this.question = question;
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
