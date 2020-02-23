package com.simonojok19.techman;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String studentName;

    @ColumnInfo(name = "class")
    private String studentClass;

    @ColumnInfo(name = "school")
    private String studentSchool;

    @ColumnInfo(name = "district")
    private String studentDistrict;

    public Student(@NonNull String studentName, String studentClass, String studentSchool, String studentDistrict) {
        this.studentName = studentName;
        this.studentDistrict = studentDistrict;
        this.studentClass = studentClass;
        this.studentSchool = studentSchool;
    }

    @NonNull
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(@NonNull String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    public String getStudentDistrict() {
        return studentDistrict;
    }

    public void setStudentDistrict(String studentDistrict) {
        this.studentDistrict = studentDistrict;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
