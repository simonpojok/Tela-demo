package com.simonojok19.techman;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> students;


    StudentRepository(Application application) {
        StudentRoomDB db = StudentRoomDB.getDatabase(application);
        studentDao = db.getStudentDao();
        students = studentDao.getStudents();
    }
}
