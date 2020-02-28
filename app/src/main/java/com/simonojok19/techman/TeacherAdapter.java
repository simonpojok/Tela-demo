package com.simonojok19.techman;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.StudentViewHolder> {

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

        public void bindStudent(Teacher teacher, final OnStudentClickListener listener) {
            Log.d("RecyclerView", "=====  Binding Data === ");
            studentName.setText(teacher.getStudentName());
            studentSchool.setText(teacher.getStudentSchool());
            studentDistrict.setText(teacher.getStudentDistrict());
            itemHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStudentClick(teacher);
                }
            });
        }
    }

    private final LayoutInflater layoutInflater;
    private List<Teacher> teachers;
    private OnStudentClickListener listener;

    public interface OnStudentClickListener {
        void onStudentClick(Teacher teacher);
    }


    TeacherAdapter(Context context, OnStudentClickListener listener) {
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
        Teacher current = teachers.get(position);
        holder.bindStudent(current, listener);
    }

    @Override
    public int getItemCount() {
        if ( teachers != null ) {
            return teachers.size();
        } else {
            return 0;
        }
    }

    void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
        notifyDataSetChanged();
    }

    public Teacher getStudentAtPosition(int position) {
        return teachers.get(position);
    }
}
