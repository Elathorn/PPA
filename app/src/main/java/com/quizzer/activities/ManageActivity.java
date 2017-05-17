package com.quizzer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.quizzer.R;

public class ManageActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button btnAddQuiz;
    private Button btnDeleteQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz);
        btnDeleteQuiz = (Button) findViewById(R.id.btnDeleteQuiz);

        btnDeleteQuiz.setOnClickListener(this);
        btnAddQuiz.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnAddQuiz) {

        }

        if (v == btnDeleteQuiz) {

        }
    }
}
