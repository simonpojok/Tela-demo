package com.simonojok19.techman;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = { Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    private Context context;
    private static StudentDatabase instance;
}
