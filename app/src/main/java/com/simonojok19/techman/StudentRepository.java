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

    LiveData<List<Student>> getStudents() {
        return students;
    }

    void insert(Student student) {
        StudentRoomDB.databaseWriteExecuter.execute( () -> {
            studentDao.insert(student);
        });
    }

    void deleteAllStudents() {
        StudentRoomDB.databaseWriteExecuter.execute(() -> {
            studentDao.deleteStudents();
        });
    }

    void deleteStudent(Student student) {
        StudentRoomDB.databaseWriteExecuter.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }
}
