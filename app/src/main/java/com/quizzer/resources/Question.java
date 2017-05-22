package com.quizzer.resources;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Elas PC on 2017-05-17.
 */

public class Question implements Serializable {
    String name;
    List<Answer> answers;
    int id;

    static public final String Tag = "QUESTION";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getNumberOfAnswers() {
        return answers.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question(String name, List<Answer> answers, int id) {

        this.name = name;
        this.answers = answers;
        this.id = id;
    }

    public Question() { }
}
