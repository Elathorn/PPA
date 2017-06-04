package com.quizzer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quizzer.resources.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elas PC on 2017-06-04.
 */

public class DeleteQuizAdapter extends RecyclerView.Adapter<DeleteQuizAdapter.DeleteQuizViewHolder> {
    private List<Quiz> quizList = new ArrayList<>();

    public class DeleteQuizViewHolder extends RecyclerView.ViewHolder {
        public TextView tvQuizName;
        public Button btnDelete;

        DeleteQuizViewHolder(View view) {
            super(view);
            tvQuizName = (TextView) view.findViewById(R.id.tvThisQuizName);
            btnDelete = (Button) view.findViewById(R.id.btnRemoveThisQuiz);
        }
    }

    public DeleteQuizAdapter(List<Quiz> quizList) {
        this.quizList = quizList;
    }
    @Override
    public void onBindViewHolder(DeleteQuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.tvQuizName.setText(quiz.getName());
    }

    @Override
    public DeleteQuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_with_delete, parent, false);

        return new DeleteQuizViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}
