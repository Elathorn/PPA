package com.quizzer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.quizzer.LinearRecyclerAdapter;
import com.quizzer.R;
import com.quizzer.resources.Question;
import com.quizzer.resources.Quiz;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Quiz quiz;

    private Button btnSave;
    private EditText edtQuizName;
    private RecyclerView recyclerViewQuizList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton fab;

    static final String QUESTION_INTENT_NAME = "QUESTION_INTENT_NAME";
    static final int CREATE_QUESTION_REQUEST_INT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.btnSaveQuiz);
        btnSave.setOnClickListener(this);
        edtQuizName = (EditText) findViewById(R.id.edtQuizName);
        quiz = new Quiz(edtQuizName.getText().toString().trim());

        recyclerViewQuizList = (RecyclerView) findViewById(R.id.recyclerViewQuizList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewQuizList.setLayoutManager(mLayoutManager);
        refreshRecyclerView();
    }


    private void refreshRecyclerView() {
        if (quiz.getQuestions() == null)
            return;
        mAdapter = new LinearRecyclerAdapter(quiz.getQuestionsNames(), R.layout.row);
        recyclerViewQuizList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void emptyQuizNameAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Invalid quiz name");
        alertDialogBuilder.setMessage("Quiz must have a name").
                setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            if (TextUtils.isEmpty(edtQuizName.getText())) {
                emptyQuizNameAlert();
                return;
            }
            quiz.setName(edtQuizName.getText().toString().trim());
            quiz.saveToFirebase();
            finish();
        }

        if (v == fab) {
            startActivityForResult(new Intent(this, CreateQuestionActivity.class), CREATE_QUESTION_REQUEST_INT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_QUESTION_REQUEST_INT) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                Question question = (Question)bundle.getSerializable(QUESTION_INTENT_NAME);
                quiz.AddQuestion(question);
                refreshRecyclerView();
            }
        }
    }
}
