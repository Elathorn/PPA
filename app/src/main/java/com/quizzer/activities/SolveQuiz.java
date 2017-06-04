package com.quizzer.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quizzer.R;
import com.quizzer.resources.Answer;
import com.quizzer.resources.Question;
import com.quizzer.resources.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolveQuiz extends AppCompatActivity implements View.OnClickListener {
    private List<Button> btnAnswers = new ArrayList<>();
    private TextView tvQuestion;
    private DatabaseReference mDatabase;
    private ViewAnimator vaSolveQuiz;
    Random random = new Random();
    List<Quiz> quizList = new ArrayList<>();
    Quiz quiz;
    List<Answer> currentAnswersSet;

    int numberOfQuestions;
    int numberOfAnswers;
    int numberOfCorrectAnswers;

    private List<Answer> shuffleAnswers(List<Answer> answers) {
        List<Answer> shuffledAnswers = new ArrayList<>();
        while (!answers.isEmpty()) {
            int pos = random.nextInt(answers.size());
            shuffledAnswers.add(answers.get(pos));
            answers.remove(pos);
        }
        return shuffledAnswers;
    }

    private void setVisibilityForAllControls(int visibility) {
        for (Button button : btnAnswers) {
            button.setVisibility(visibility);
        }
        tvQuestion.setVisibility(visibility);
    }

    private void fillFormsWithQuestion (Question question) {
        setVisibilityForAllControls(View.VISIBLE);
        List<Answer> answers = shuffleAnswers(question.getAnswers());
        int answersNumber = answers.size();
        tvQuestion.setText(question.getName());
        currentAnswersSet = answers;
        for (int i = 0; i < answersNumber; i++)
            btnAnswers.get(i).setText(answers.get(i).getAnswer());
        for (int i = 5; i > answersNumber -1; i--) {
            btnAnswers.get(i).setVisibility(View.INVISIBLE);
        }
    }

    private void nextQuestion() {
        if (numberOfAnswers < numberOfQuestions) {
            fillFormsWithQuestion(quiz.getQuestions().get(numberOfAnswers));
        }
        else {
            quizEnderAlert();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_quiz);

        vaSolveQuiz = (ViewAnimator) findViewById(R.id.vaSolveQuiz);
        btnAnswers.add((Button) findViewById(R.id.btnAnsw1));
        btnAnswers.add((Button) findViewById(R.id.btnAnsw2));
        btnAnswers.add((Button) findViewById(R.id.btnAnsw3));
        btnAnswers.add((Button) findViewById(R.id.btnAnsw4));
        btnAnswers.add((Button) findViewById(R.id.btnAnsw5));
        btnAnswers.add((Button) findViewById(R.id.btnAnsw6));
        for (Button button : btnAnswers)
            button.setOnClickListener(this);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        setUpDB();
        setVisibilityForAllControls(View.INVISIBLE);
        numberOfAnswers = 0;
    }

    private void selectRandomQuiz() {
        int pos = random.nextInt(quizList.size());
        quiz = quizList.get(pos);
        numberOfQuestions = quiz.getQuestions().size();
    }

    private void noQuizzesFoundAlert() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Can't get any quiz");
            alertDialogBuilder.setMessage("Can't find any quiz. Add a quiz if you haven't added any. If you have quizzes, check your internet connection.").
                    setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            alertDialogBuilder.show();
        }

    private void quizEnderAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Quiz ended!");
        String text = String.format("Number of questions: %d \nCorrect answers: %d.", numberOfQuestions, numberOfCorrectAnswers);
        alertDialogBuilder.setMessage(text).
                setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialogBuilder.show();
    }

    private void setUpDB() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.child("quiz").getChildren()) {
                    Quiz quiz = data.getValue(Quiz.class);
                    quiz.setUid(data.getKey());
                    quizList.add(quiz);
                }
                if (quizList.size() == 0) {
                    noQuizzesFoundAlert();
                    return;
                }
                vaSolveQuiz.showNext();
                selectRandomQuiz();
                nextQuestion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("SolveQuiz", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        for (Answer answer : currentAnswersSet) {
            if (button.getText().toString() == answer.getAnswer()) {
                if (answer.isCorrect())
                    numberOfCorrectAnswers++;
            }
        }
        numberOfAnswers++;
        nextQuestion();
    }
}
