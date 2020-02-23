package com.simonojok19.techman;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    private static volatile StudentDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    static StudentDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        StudentDatabase.class, "student_database").build();
            }
        }
        return INSTANCE;
    }
}
