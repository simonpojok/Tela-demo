package com.simonojok19.techman;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "student_table")
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String studentName;

    @ColumnInfo(name = "class")
    private String studentClass;

    @ColumnInfo(name = "school")
    private String studentSchool;

    @ColumnInfo(name = "district")
    private String studentDistrict;

    public Student(String studentName, String studentClass, String studentSchool, String studentDistrict) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentSchool = studentSchool;
        this.studentDistrict = studentDistrict;
    }

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public String getStudentDistrict() {
        return studentDistrict;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    public void setStudentDistrict(String studentDistrict) {
        this.studentDistrict = studentDistrict;
    }
}
