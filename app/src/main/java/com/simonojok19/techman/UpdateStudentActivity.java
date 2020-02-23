package com.simonojok19.techman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudentActivity extends AppCompatActivity {
    public static final String STUDENT_ID = "com.simonojok19.techman.STUDENT_ID";
    public static final String STUDENT_NAME = "com.simonojok19.techman.STUDENT_NAME";
    public static final String STUDENT_CLASS = "com.simonojok19.techman.STUDENT_CLASS";
    public static final String STUDENT_SCHOOL = "com.simonojok19.techman.STUDENT_SCHOOL";
    public static final String STUDENT_DISTRICT = "com.simonojok19.techman.STUDENT_DISTRICT";

    private EditText studentName;
    private EditText studentClass;
    private EditText studentSchool;
    private EditText studentDistrict;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        Intent intent = getIntent();
        if (intent != null ) {
            student = new Student(
                    intent.getStringExtra(STUDENT_NAME),
                    intent.getStringExtra(STUDENT_CLASS),
                    intent.getStringExtra(STUDENT_SCHOOL),
                    intent.getStringExtra(STUDENT_DISTRICT)
            );
            student.setId(intent.getIntExtra(STUDENT_ID, -1));
            Toast.makeText(this, intent.getStringExtra(STUDENT_NAME), Toast.LENGTH_SHORT).show();
        }

        studentName = findViewById(R.id.update_name);
        studentClass = findViewById(R.id.update_class);
        studentSchool = findViewById(R.id.update_school);
        studentDistrict = findViewById(R.id.update_district);

        studentName.setText(student.getStudentName());
        studentClass.setText(student.getStudentClass());
        studentSchool.setText(student.getStudentSchool());
        studentDistrict.setText(student.getStudentDistrict());
    }
}
