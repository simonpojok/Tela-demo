package co.planetsystems.tela;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class TeacherListActivity extends AppCompatActivity  implements TeacherAdapter.OnTeacherClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
    }

    @Override
    public void onTeacherClick(Teacher teacher) {
        Toast.makeText(this, teacher.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
