package co.planetsystems.tela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class TeacherListActivity extends AppCompatActivity  implements TeacherAdapter.OnTeacherClickListener {
    TeacherViewModel teacherViewModel;
    RecyclerView recyclerView;
    TeacherAdapter teacherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        teacherViewModel = new ViewModelProvider(this).get(TeacherViewModel.class);
        recyclerView = findViewById(R.id.teachers_list);
        teacherAdapter =  new TeacherAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(teacherAdapter);

        teacherViewModel.getTeachers().observe(this, new Observer<List<Teacher>>() {
            @Override
            public void onChanged(List<Teacher> teachers) {
                Toast.makeText(TeacherListActivity.this, String.valueOf(teachers.size()), Toast.LENGTH_SHORT).show();
                teacherAdapter.setTeachers(teachers);
            }
        });
    }

    @Override
    public void onTeacherClick(Teacher teacher) {
        Toast.makeText(this, teacher.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
