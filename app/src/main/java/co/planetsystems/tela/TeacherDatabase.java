package co.planetsystems.tela;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Teacher.class, Attendance.class}, version = 1, exportSchema = false)
public abstract class TeacherDatabase extends RoomDatabase {
    public abstract TeacherDao getTeacherDao();
    public abstract AttendanceDao getAttendanceDao();

    private static volatile TeacherDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecuter =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    static TeacherDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TeacherDatabase.class) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        TeacherDatabase.class,
                        "teacher_database"
                ).build();
            }
        }
        return INSTANCE;
    }
}
