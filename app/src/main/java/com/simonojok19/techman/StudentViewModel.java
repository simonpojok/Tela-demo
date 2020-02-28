package com.simonojok19.techman;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Teacher>> students;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        students = repository.getStudents();
    }

    LiveData<List<Teacher>> getStudents() {
        return students;
    }

    void insert(Teacher teacher) {
        repository.insert(teacher);
    }

    void deleteAllStudents() {
        repository.deleteAllStudents();
    }

    void deleteStudent(Teacher teacher) {
        repository.deleteStudent(teacher);
    }

    void updateStudent(Teacher teacher) {
        repository.updateStudent(teacher);
    }
}
