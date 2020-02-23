package com.simonojok19.techman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {
    private EditText studentName;
    private EditText studentClass;
    private EditText studentSchool;
    private EditText studentDistrict;

    public static final String STUDENT_NAME = "com.simonojok19.techman.STUDENT_NAME";
    public static final String STUDENT_CLASS = "com.simonojok19.techman.STUDENT_CLASS";
    public static final String STUDENT_SCHOOL = "com.simonojok19.techman.STUDENT_SCHOOL";
    public static final String STUDENT_DISTRICT = "com.simonojok19.techman.STUDENT_DISTRICT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        studentName = findViewById(R.id.student_name);
        studentClass = findViewById(R.id.student_class);
        studentSchool = findViewById(R.id.student_school);
        studentDistrict = findViewById(R.id.student_district);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent();
                intent.putExtra(STUDENT_NAME, studentName.getText().toString());
                intent.putExtra(STUDENT_CLASS, studentClass.getText().toString());
                intent.putExtra(STUDENT_SCHOOL, studentSchool.getText().toString());
                intent.putExtra(STUDENT_DISTRICT, studentDistrict.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
