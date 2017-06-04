package com.quizzer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.quizzer.R;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button buttonManageQuizzes;
    private Button buttonStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonManageQuizzes = (Button) findViewById(R.id.btnManageQuizzes);
        buttonStartQuiz = (Button) findViewById(R.id.btnStartQuiz);

        buttonManageQuizzes.setOnClickListener(this);
        buttonStartQuiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == buttonStartQuiz) {
            startActivity(new Intent(this, SolveQuiz.class));
        }

        if (view == buttonManageQuizzes) {
            startActivity(new Intent(this, ManageActivity.class));
        }

    }
}
