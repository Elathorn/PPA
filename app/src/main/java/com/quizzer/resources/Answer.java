package com.quizzer.resources;

/**
 * Created by Elas PC on 2017-05-17.
 */

public class Answer {
    String answer;
    int id;
    boolean correct;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Answer(String answer, int id, boolean correct) {

        this.answer = answer;
        this.id = id;
        this.correct = correct;
    }

    public Answer(String answer, int id) {
        this.answer = answer;
        this.id = id;
        correct = false;
    }

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
