package co.planetsystems.tela;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Teacher.class, Attendance.class}, version = 1, exportSchema = false)
public abstract class TeacherDatabase extends RoomDatabase {
    public abstract TeacherDao getTeacherDao();
    public abstract AttendanceDao getAttendanceDao();
}
