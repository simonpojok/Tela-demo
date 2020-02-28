package com.simonojok19.techman;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Teacher>> students;


    StudentRepository(Application application) {
        StudentRoomDB db = StudentRoomDB.getDatabase(application);
        studentDao = db.getStudentDao();
        students = studentDao.getStudents();
    }

    LiveData<List<Teacher>> getStudents() {
        return students;
    }

    void insert(Teacher teacher) {
        StudentRoomDB.databaseWriteExecuter.execute( () -> {
            studentDao.insert(teacher);
        });
    }

    void deleteAllStudents() {
        StudentRoomDB.databaseWriteExecuter.execute(() -> {
            studentDao.deleteStudents();
        });
    }

    void deleteStudent(Teacher teacher) {
        StudentRoomDB.databaseWriteExecuter.execute(() -> {
            studentDao.deleteStudent(teacher);
        });
    }

    void  updateStudent(Teacher teacher) {
        StudentRoomDB.databaseWriteExecuter.execute(() -> {
            studentDao.updateStudent(teacher);
        });
    }
}
