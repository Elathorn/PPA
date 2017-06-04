package com.quizzer.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quizzer.R;
import com.quizzer.resources.Answer;
import com.quizzer.resources.Question;

import java.util.ArrayList;
import java.util.List;

import static com.quizzer.activities.CreateQuizActivity.CREATE_QUESTION_REQUEST_INT;
import static com.quizzer.activities.CreateQuizActivity.QUESTION_INTENT_NAME;

public class CreateQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtCorrectAnswer;
    List<EditText> edtIncorrectAnswers;
    EditText edtQuestionName;
    Intent intent;
    Button okButton;
    final Context context = this;

    Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        edtCorrectAnswer = (EditText) findViewById(R.id.edtCorrectAnswer);
        question = new Question();

        edtQuestionName = (EditText) findViewById(R.id.edtQuestionName);

        edtIncorrectAnswers = new ArrayList<>();
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtIncorrectAnswer));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer1));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer2));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer3));
        edtIncorrectAnswers.add((EditText) findViewById(R.id.edtOptionalAnswer4));

        okButton = (Button) findViewById(R.id.add_question);
        okButton.setOnClickListener(this);

        intent = this.getIntent();
    }

    void createAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Invalid question");
        alertDialogBuilder.setMessage("You need at least question, valid answer and invalid answer").
                setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.show();
    }

    void createQuestionAndEndActivity() {
        question.setName(edtQuestionName.getText().toString());
        List<Answer> answers = new ArrayList<>();

        Answer answer = new Answer(edtCorrectAnswer.getText().toString(), true);
        answers.add(answer);

        for (EditText editText : edtIncorrectAnswers) {
            if (TextUtils.isEmpty(editText.getText()))
                break;
            answer = new Answer(editText.getText().toString(), false);
            answers.add(answer);
        }

        question.setAnswers(answers);
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTION_INTENT_NAME, question);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == okButton) {
            if (TextUtils.isEmpty(edtCorrectAnswer.getText())
                    || TextUtils.isEmpty(edtIncorrectAnswers.get(0).getText())
                    || TextUtils.isEmpty(edtQuestionName.getText())) {
                createAlertDialog();
                return;
            }
            else {
                createQuestionAndEndActivity();
            }
        }

    }
}
