package com.simonojok19.techman;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Student>> students;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        students = repository.getStudents();
    }

    LiveData<List<Student>> getStudents() {
        return students;
    }

    void insert(Student student) {
        repository.insert(student);
    }

    void deleteAllStudents() {
        repository.deleteAllStudents();
    }

    void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }

    void updateStudent(Student student) {
        repository.updateStudent(student);
    }
}
