package co.planetsystems.tela;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TeacherAttendance {
    @Embedded
    public Teacher teacher;
    @Relation(
            parentColumn = "nationalID",
            entityColumn = "teacher_nin",
            entity = Attendance.class
    )
    public LiveData<List<Attendance>> attendances;
}
