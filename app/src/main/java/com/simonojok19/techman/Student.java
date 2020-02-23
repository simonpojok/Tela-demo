package com.simonojok19.techman;

import androidx.annotation.NonNull;

public class Student {
    private String studentName;
    private String studentClass;
    private String studentSchool;
    private String studentDistrict;

    public Student(@NonNull String studentName, String studentClass, String studentSchool, String studentDistrict) {
        this.studentName = studentName;
        this.studentDistrict = studentDistrict;
        this.studentClass = studentClass;
        this.studentSchool = studentSchool;
    }
}
