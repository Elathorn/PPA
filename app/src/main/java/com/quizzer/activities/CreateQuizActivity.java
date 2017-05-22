package com.quizzer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.quizzer.R;
import com.quizzer.resources.Question;
import com.quizzer.resources.Quiz;

import java.util.ArrayList;
import java.util.Arrays;

import static android.R.id.list;

public class CreateQuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Quiz quiz;

    private EditText edtQuizName;
    private ListView listViewQuizList;
    private FloatingActionButton fab;

    private ArrayAdapter<String> adapter;

    static final int CREATE_QUESTION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);


        edtQuizName = (EditText) findViewById(R.id.edtQuizName);
        Quiz quiz = new Quiz(edtQuizName.getText().toString().trim());
        //edtQuizName.setOnClickListener(this);

        listViewQuizList = (ListView) findViewById(R.id.listViewQuizList);
        refreshListView();
    }


    private void refreshListView() {
        if (quiz == null)
            return;
        adapter = new ArrayAdapter<>(this, R.layout.row, quiz.getQuestionsNames());
        listViewQuizList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == edtQuizName) {
            quiz.setName(edtQuizName.getText().toString().trim());
        }

        if (v == fab) {
            startActivityForResult(new Intent(this, CreateQuestionActivity.class), CREATE_QUESTION_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_QUESTION_REQUEST) {
            if (resultCode == RESULT_OK) {
                data.getSerializableExtra(Question.Tag);
            }
        }
    }
}
