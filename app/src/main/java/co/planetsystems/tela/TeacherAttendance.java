package co.planetsystems.tela;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TeacherAttendance {
    @Embedded
    public Teacher teacher;
    @Relation(
            parentColumn = "nationID",
            entityColumn = "id"
    )
    public LiveData<List<Attendance>> attendances;
}
