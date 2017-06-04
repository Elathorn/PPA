package com.quizzer.resources;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Elas PC on 2017-05-17.
 */

public class Quiz implements Serializable {
    String uid;
    String name;
    List<Question> questions;
    
    public ArrayList<String> getQuestionsNames() {
        ArrayList<String> list = new ArrayList<>();
        for (Question question: questions) {
            list.add(question.getName());
        }
        return list;
    }

    public Quiz() {

    }

    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void AddQuestion(Question question) {
        questions.add(question);
    }

    public void saveToFirebase() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String key = database.child("posts").push().getKey();
        HashMap<String, Object> values = this.toHashMap();
        Map<String, Object> update = new HashMap<>();
        update.put("/quiz/" + key, values);
        database.updateChildren(update);
        if (uid == null)
            uid = key;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("questions", questions);
        result.put("name", name);
        return result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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


    public Quiz(String uid, String name, List<Question> questions) {
        this.uid = uid;
        this.name = name;
        this.questions = questions;
    }
}
