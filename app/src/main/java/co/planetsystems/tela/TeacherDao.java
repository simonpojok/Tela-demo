package co.planetsystems.tela;

import androidx.lifecycle.LiveData;
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
    LiveData<List<Teacher>> getTeachers();

    @Delete
    void deleteTeacher(Teacher teacher);

    @Update
    void updateTeacher(Teacher teacher);

//    @Query("SELECT * FROM teacher_table")
//    LiveData<List<TeacherAttendance>> getTeacherAttendancies();
}
