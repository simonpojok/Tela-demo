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
}
