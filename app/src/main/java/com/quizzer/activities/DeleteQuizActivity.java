package com.quizzer.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.quizzer.DeleteQuizAdapter;
import com.quizzer.LinearRecyclerAdapter;
import com.quizzer.R;
import com.quizzer.resources.Quiz;

import java.util.ArrayList;
import java.util.List;

public class DeleteQuizActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private List<Quiz> quizList = new ArrayList<>();
    private ValueEventListener valueEventListener;
    private RecyclerView recyclerViewQuizList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpDB();
        setContentView(R.layout.activity_delete_quiz);

        recyclerViewQuizList = (RecyclerView) findViewById(R.id.recyclerViewDeleteQuiz);
        mAdapter = new DeleteQuizAdapter(quizList);
        mLayoutManager = new LinearLayoutManager(this); //getAplicationContext() ?
        recyclerViewQuizList.setLayoutManager(mLayoutManager);
        recyclerViewQuizList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewQuizList.setAdapter(mAdapter);

        refreshRecyclerView();
    }

    private void refreshRecyclerView() {
        mAdapter = new DeleteQuizAdapter(quizList);
        recyclerViewQuizList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void setUpDB() {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.child("quiz").getChildren()) {
                    Quiz quiz = data.getValue(Quiz.class);
                    quiz.setUid(data.getKey());
                    quizList.add(quiz);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(valueEventListener);
    }


    private void deleteQuestionAlert(int pos) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Are you sure you want to delete quiz?");
        alertDialogBuilder.setMessage("This operation can't be reversed.").
                setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Query query = mDatabase.child("quiz").orderByKey().equalTo("string");
                                query.getRef().removeValue();
                            }
                        }
                );
        alertDialogBuilder.show();
    }
}
