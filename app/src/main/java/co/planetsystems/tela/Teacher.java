package co.planetsystems.tela;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teacher_table")
public class Teacher {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nationalID")
    private String nationID;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "telephone")
    private String telephone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "school")
    private String school;

    @ColumnInfo(name = "district")
    private String district;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "thumbImage")
    private byte[] thumbImage;

    @ColumnInfo(name = "fingerPrint")
    private byte[] fingerPrint;

    @NonNull
    public String getNationID() {
        return nationID;
    }

    public Teacher(@NonNull String nationID, String firstName, String lastName, String telephone, String email, String gender, String school, String district, String role, byte[] thumbImage, byte[] fingerPrint) {
        this.nationID = nationID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.gender = gender;
        this.school = school;
        this.district = district;
        this.role = role;
        this.thumbImage = thumbImage;
        this.fingerPrint = fingerPrint;
    }

    public void setNationID(@NonNull String nationID) {
        this.nationID = nationID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(byte[] thumbImage) {
        this.thumbImage = thumbImage;
    }

    public byte[] getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(byte[] fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
