package com.simonojok19.techman;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("DELETE FROM student_table")
    void deleteStudents();

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    LiveData<List<Student>> getStudents();

    @Query("SELECT * FROM student_table LIMIT 1")
    Student[] getAnyStudent();
}
