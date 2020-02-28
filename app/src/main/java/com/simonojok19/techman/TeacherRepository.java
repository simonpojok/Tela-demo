package com.simonojok19.techman;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TeacherRepository {
    private TeacherDao teacherDao;
    private LiveData<List<Teacher>> students;


    TeacherRepository(Application application) {
        TeacherDatabase db = TeacherDatabase.getDatabase(application);
        teacherDao = db.getStudentDao();
        students = teacherDao.getStudents();
    }

    LiveData<List<Teacher>> getStudents() {
        return students;
    }

    void insert(Teacher teacher) {
        TeacherDatabase.databaseWriteExecuter.execute( () -> {
            teacherDao.insert(teacher);
        });
    }

    void deleteAllStudents() {
        TeacherDatabase.databaseWriteExecuter.execute(() -> {
            teacherDao.deleteStudents();
        });
    }

    void deleteStudent(Teacher teacher) {
        TeacherDatabase.databaseWriteExecuter.execute(() -> {
            teacherDao.deleteStudent(teacher);
        });
    }

    void  updateStudent(Teacher teacher) {
        TeacherDatabase.databaseWriteExecuter.execute(() -> {
            teacherDao.updateStudent(teacher);
        });
    }
}
