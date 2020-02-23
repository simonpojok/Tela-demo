package com.simonojok19.techman;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentClickListener{
    private StudentViewModel studentViewModel;
    public static final int NEW_STUDENT_ACTIVITY = 348;
    public static final int UPDATE_STUDENT_ACTIVITY = 3496;
    StudentAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, NEW_STUDENT_ACTIVITY);
            }
        });

        recyclerView = findViewById(R.id.recyclerView_students);
        adapter = new StudentAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentViewModel.getStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setStudents(students);
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
                Student student = adapter.getStudentAtPosition(position);
                Toast.makeText(MainActivity.this, "Student " + student.getStudentName() + " was deleted", Toast.LENGTH_SHORT).show();
                studentViewModel.deleteStudent(student);
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
        if (requestCode == NEW_STUDENT_ACTIVITY && resultCode == RESULT_OK) {
            Student student = new Student(
                    data.getStringExtra(AddStudentActivity.STUDENT_NAME),
                    data.getStringExtra(AddStudentActivity.STUDENT_CLASS),
                    data.getStringExtra(AddStudentActivity.STUDENT_SCHOOL),
                    data.getStringExtra(AddStudentActivity.STUDENT_DISTRICT)
            );
            studentViewModel.insert(student);
        }

        if (requestCode == UPDATE_STUDENT_ACTIVITY && resultCode == RESULT_OK) {
            Student student = new Student(
                    data.getStringExtra(UpdateStudentActivity.STUDENT_NAME),
                    data.getStringExtra(UpdateStudentActivity.STUDENT_CLASS),
                    data.getStringExtra(UpdateStudentActivity.STUDENT_SCHOOL),
                    data.getStringExtra(UpdateStudentActivity.STUDENT_DISTRICT)
            );
            student.setId(data.getIntExtra(UpdateStudentActivity.STUDENT_ID, -1));
            Toast.makeText(this, "Student " + student.getStudentName() + " was updated", Toast.LENGTH_SHORT).show();
            studentViewModel.updateStudent(student);
        }
    }

    @Override
    public void onStudentClick(Student student) {
        Intent intent = new Intent(MainActivity.this, UpdateStudentActivity.class);
        intent.putExtra(UpdateStudentActivity.STUDENT_ID, student.getId());
        intent.putExtra(UpdateStudentActivity.STUDENT_NAME, student.getStudentName());
        intent.putExtra(UpdateStudentActivity.STUDENT_CLASS, student.getStudentClass());
        intent.putExtra(UpdateStudentActivity.STUDENT_SCHOOL, student.getStudentSchool());
        intent.putExtra(UpdateStudentActivity.STUDENT_DISTRICT, student.getStudentDistrict());
        startActivityForResult(intent, UPDATE_STUDENT_ACTIVITY);
    }
}
