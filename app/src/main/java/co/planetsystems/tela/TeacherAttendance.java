package co.planetsystems.tela;

import androidx.room.Embedded;
import androidx.room.Relation;

public class TeacherAttendance {
    @Embedded
    private Teacher teacher;
    @Relation(
            parentColumn = "nationID",
            entityColumn = "id"
    )
    private Attendance attendance;

    public TeacherAttendance(Teacher teacher, Attendance attendance) {
        this.teacher = teacher;
        this.attendance = attendance;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
