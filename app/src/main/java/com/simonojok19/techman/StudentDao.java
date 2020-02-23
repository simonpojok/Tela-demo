package com.simonojok19.techman;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.stringtemplate.v4.ST;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getAll();

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);
}
