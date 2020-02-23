package com.simonojok19.techman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private final Context context;
    private List<Student> mStudents;

    StudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        if (mStudents != null) {
            Student student = mStudents.get(position);
            holder.studentName.setText(student.getStudentName());
            holder.studentSchool.setText(student.getStudentSchool());
            holder.studentDistrict.setText(student.getStudentDistrict());
        }
    }

    void setStudents(List<Student> students) {
        mStudents = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mStudents != null) {
            return mStudents.size();
        }
        else return 0;
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView studentName;
        private final TextView studentSchool;
        private final TextView studentDistrict;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.text_name);
            studentSchool = itemView.findViewById(R.id.text_school);
            studentDistrict = itemView.findViewById(R.id.text_district);
        }
    }

}
