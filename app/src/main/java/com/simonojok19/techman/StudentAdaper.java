package com.simonojok19.techman;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdaper extends RecyclerView.Adapter<StudentAdaper.StudentViewHold> {

    public class StudentViewHold extends RecyclerView.ViewHolder{
        public StudentViewHold(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public StudentViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHold holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
