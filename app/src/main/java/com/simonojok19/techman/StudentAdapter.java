package com.simonojok19.techman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private final TextView studentName;
        private final TextView studentSchool;
        private final TextView studentDistrict;
        private final View itemHolder;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemHolder = itemView;
            studentName = itemView.findViewById(R.id.text_name);
            studentSchool = itemView.findViewById(R.id.text_school);
            studentDistrict = itemView.findViewById(R.id.text_district);
        }

        public void bindStudent(Student student, final OnStudentClickListener listener) {
            Log.d("RecyclerView", "=====  Binding Data === ");
            studentName.setText(student.getStudentName());
            studentSchool.setText(student.getStudentSchool());
            studentDistrict.setText(student.getStudentDistrict());
            itemHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStudentClick(student);
                }
            });
        }
    }

    private final LayoutInflater layoutInflater;
    private List<Student> students;
    private OnStudentClickListener listener;

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }


    StudentAdapter(Context context, OnStudentClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student current = students.get(position);
        holder.bindStudent(current, listener);
    }

    @Override
    public int getItemCount() {
        if ( students != null ) {
            return students.size();
        } else {
            return 0;
        }
    }

    void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public Student getStudentAtPosition(int position) {
        return students.get(position);
    }
}
