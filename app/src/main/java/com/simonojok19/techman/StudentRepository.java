package com.simonojok19.techman;

import android.app.Application;
import android.os.AsyncTask;

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
//        studentDao.insert(student);
        StudentDatabase.databaseWriteExecutor.execute( () -> {
            studentDao.insert(student);
        });
//        new LoadDataTask().execute(student);

    }

    public class LoadDataTask extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students[0]);
            return null;
        }
    }
}
