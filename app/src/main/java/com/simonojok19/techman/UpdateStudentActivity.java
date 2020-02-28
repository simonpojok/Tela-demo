package com.simonojok19.techman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        Intent intent = getIntent();
        if (intent != null ) {
            teacher = new Teacher(
                    intent.getStringExtra(STUDENT_NAME),
                    intent.getStringExtra(STUDENT_CLASS),
                    intent.getStringExtra(STUDENT_SCHOOL),
                    intent.getStringExtra(STUDENT_DISTRICT)
            );
            teacher.setId(intent.getIntExtra(STUDENT_ID, -1));
        }

        studentName = findViewById(R.id.update_name);
        studentClass = findViewById(R.id.update_class);
        studentSchool = findViewById(R.id.update_school);
        studentDistrict = findViewById(R.id.update_district);

        studentName.setText(teacher.getStudentName());
        studentClass.setText(teacher.getStudentClass());
        studentSchool.setText(teacher.getStudentSchool());
        studentDistrict.setText(teacher.getStudentDistrict());

        final Button button = findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher.setStudentName(studentName.getText().toString());
                teacher.setStudentClass(studentClass.getText().toString());
                teacher.setStudentSchool(studentSchool.getText().toString());
                teacher.setStudentDistrict(studentDistrict.getText().toString());
                Intent out = new Intent();
                out.putExtra(STUDENT_ID, teacher.getId());
                out.putExtra(STUDENT_NAME, teacher.getStudentName());
                out.putExtra(STUDENT_CLASS, teacher.getStudentClass());
                out.putExtra(STUDENT_SCHOOL, teacher.getStudentSchool());
                out.putExtra(STUDENT_DISTRICT, teacher.getStudentDistrict());
                setResult(RESULT_OK, out);
                finish();
            }
        });
    }
}
