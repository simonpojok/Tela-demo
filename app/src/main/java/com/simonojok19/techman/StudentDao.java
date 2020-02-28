package com.simonojok19.techman;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Teacher teacher);

    @Query("DELETE FROM Teacher")
    void deleteStudents();

    @Query("SELECT * FROM Teacher ORDER BY id ASC")
    LiveData<List<Teacher>> getStudents();

    @Query("SELECT * FROM Teacher LIMIT 1")
    Teacher[] getAnyStudent();

    @Delete
    void deleteStudent(Teacher teacher);

    @Update
    void updateStudent(Teacher teacher);
}
