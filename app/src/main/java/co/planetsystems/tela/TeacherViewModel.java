package co.planetsystems.tela;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TeacherViewModel extends AndroidViewModel {
    private TeacherRespository respository;
    private LiveData<List<Teacher>> teachers;
    private LiveData<List<Attendance>> attendances;


    public TeacherViewModel(@NonNull Application application) {
        super(application);
        respository = new TeacherRespository(application);
        teachers = respository.getTeachers();
    }

    void enrollTeacher(Teacher teacher) {
        respository.enrollTeacher(teacher);
    }

    void addAttendance(Attendance attendance) {
        respository.addAttendance(attendance);
    }

    LiveData<List<Teacher>> getTeachers() {
        return teachers;
    }
}
