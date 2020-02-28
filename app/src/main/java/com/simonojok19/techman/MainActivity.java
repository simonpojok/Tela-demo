package com.simonojok19.techman;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import static com.simonojok19.techman.EnrollActivity.STUDENT_CLASS;
import static com.simonojok19.techman.EnrollActivity.STUDENT_IMAGE_BYTES;
import static com.simonojok19.techman.EnrollActivity.STUDENT_NAME;
import static com.simonojok19.techman.EnrollActivity.STUDENT_TEMPLATE_BYTES;

public class MainActivity extends AppCompatActivity implements TeacherAdapter.OnStudentClickListener{
    private TeacherViewModel studentViewModel;
    public static final int ENROLL_TEACHER = 348;
    public static final int VERIFY_TEACHER = 3496;
    TeacherAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentViewModel = new ViewModelProvider(this).get(TeacherViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EnrollActivity.class);
                startActivityForResult(intent, ENROLL_TEACHER);
            }
        });

        recyclerView = findViewById(R.id.recyclerView_students);
        adapter = new TeacherAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentViewModel.getStudents().observe(this, new Observer<List<Teacher>>() {
            @Override
            public void onChanged(List<Teacher> teachers) {
                adapter.setTeachers(teachers);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position =  viewHolder.getAdapterPosition();
                Teacher teacher = adapter.getStudentAtPosition(position);
                Toast.makeText(MainActivity.this, "Teacher " + teacher.getStudentName() + " was deleted", Toast.LENGTH_SHORT).show();
                studentViewModel.deleteStudent(teacher);
            }
        });

        helper.attachToRecyclerView(recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.delete_add) {
            studentViewModel.deleteAllStudents();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ENROLL_TEACHER && resultCode == RESULT_OK) {
            Teacher teacher = new Teacher(
                    data.getStringExtra(STUDENT_NAME),
                    data.getStringExtra(STUDENT_CLASS),
                    data.getStringExtra(EnrollActivity.STUDENT_SCHOOL),
                    data.getStringExtra(EnrollActivity.STUDENT_DISTRICT),
                    data.getByteArrayExtra(STUDENT_IMAGE_BYTES),
                    data.getByteArrayExtra(STUDENT_TEMPLATE_BYTES)
            );
            studentViewModel.insert(teacher);
        }

        if (requestCode == VERIFY_TEACHER && resultCode == RESULT_OK) {
//
//            teacher.setId(data.getIntExtra(UpdateStudentActivity.STUDENT_ID, -1));
//            Toast.makeText(this, "Teacher " + teacher.getStudentName() + " was updated", Toast.LENGTH_SHORT).show();
//            studentViewModel.updateStudent(teacher);
        }
    }

    @Override
    public void onStudentClick(Teacher teacher) {
        EnrollActivity.NEW_TEACTER = false;
        Intent intent = new Intent();
        intent.putExtra(EnrollActivity.STUDENT_IMAGE_BYTES, teacher.getStudentImage());
        intent.putExtra(EnrollActivity.STUDENT_TEMPLATE_BYTES, teacher.getFingerPrint());
        intent.putExtra(EnrollActivity.STUDENT_NAME, teacher.getStudentName());
        intent.putExtra(EnrollActivity.STUDENT_CLASS, teacher.getStudentClass());
        intent.putExtra(EnrollActivity.STUDENT_SCHOOL, teacher.getStudentSchool());
        intent.putExtra(EnrollActivity.STUDENT_DISTRICT, teacher.getStudentDistrict());
        startActivityForResult(intent, VERIFY_TEACHER);
    }
}
