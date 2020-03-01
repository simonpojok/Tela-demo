package co.planetsystems.tela;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TeacherRespository {
    private TeacherDao teacherDao;
    private AttendanceDao attendanceDao;
    private LiveData<List<Teacher>> teachers;
    private LiveData<List<Attendance>> attendance;

    TeacherRespository(Application application) {
        TeacherDatabase db = TeacherDatabase.getDatabase(application);
        teacherDao = db.getTeacherDao();
        attendanceDao = db.getAttendanceDao();
        teachers = teacherDao.getTeachers();
        attendance = attendanceDao.getAttendance();
    }

    LiveData<List<Teacher>> getTeachers() {
        return teachers;
    }

    void enrollTeacher(Teacher teacher) {
        TeacherDatabase.databaseWriteExecuter.execute(() -> {
            teacherDao.enrollTeacher(teacher);
        });
    }

    void addAttendance(Attendance attendance) {
        TeacherDatabase.databaseWriteExecuter.execute(() -> {
            attendanceDao.addAttendnace(attendance);
        });
    }
}
