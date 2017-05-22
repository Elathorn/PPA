package com.quizzer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.quizzer.R;
import com.quizzer.resources.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionActivity extends AppCompatActivity {

    EditText edtCorrectAnswer;
    List<EditText> edtIncorrectAnswers;

    Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        edtCorrectAnswer = (EditText) findViewById(R.id.edtCorrectAnswer);
        Question question = new Question();

        edtIncorrectAnswers = new ArrayList<>();
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtIncorrectAnswer));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer1));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer2));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer3));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer4));
    }
}
