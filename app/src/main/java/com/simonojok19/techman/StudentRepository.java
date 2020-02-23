package com.simonojok19.techman;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> students;

    StudentRepository(Application application) {
        StudentDatabase studentDatabase = StudentDatabase.getInstance(application);
        studentDao = studentDatabase.studentDao();
        students = studentDao.getAll();
    }

    LiveData<List<Student>> getStudents() {
        return students;
    }

    void insert(Student student) {
        studentDao.insert(student);
    }
}
