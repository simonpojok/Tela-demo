package co.planetsystems.tela;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TeacherRespository {
    private TeacherDao teacherDao;
    private Attendance attendanceDao;
    private LiveData<List<Teacher>> teachers;
}
