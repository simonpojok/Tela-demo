package co.planetsystems.tela;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AttendanceDao {
    @Insert
    void addAttendnace(Attendance attendance);

    @Update
    void updateAttendance(Attendance attendance);

    @Query("SELECT * FROM attendance ORDER BY date")
    LiveData<List<Attendance>> getAttendance();
}
