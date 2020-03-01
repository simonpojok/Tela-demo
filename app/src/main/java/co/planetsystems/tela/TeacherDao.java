package co.planetsystems.tela;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    void enrollTeacher(Teacher teacher);

    @Query("DELETE FROM teacher_table")
    void deleteTeachers();

    @Query("SELECT * FROM teacher_table ORDER BY nationalID ASC")
    void getTeachers();

    @Delete
    void deleteTeacher(Teacher teacher);

    @Update
    void updateTeacher(Teacher teacher);

    @Transaction
    @Query("SELECT * FROM teacher_table")
    List<TeacherAttendance> getTeacherAttendances();

    @Insert
    void addAttendnace(Attendance attendance);

    @Update
    void updateAttendance(Attendance attendance);

}
