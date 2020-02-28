package com.simonojok19.techman;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.suprema.IBioMiniDevice;

@Entity(tableName = "teacher_table")
public class Teacher {
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

    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] studentImage;

    @ColumnInfo(name = "fingerprint", typeAffinity = ColumnInfo.BLOB)
    private byte[] fingerPrint;

    public Teacher(String studentName,
                   String studentClass,
                   String studentSchool,
                   String studentDistrict,
                   byte[] studentImage,
                   byte[] fingerPrint) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentSchool = studentSchool;
        this.studentDistrict = studentDistrict;
        this.studentImage = studentImage;
        this.fingerPrint = fingerPrint;
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

    public byte[] getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(byte[] studentImage) {
        this.studentImage = studentImage;
    }

    public byte[] getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(byte[] fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
