package com.simonojok19.techman;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Teacher.class }, version = 1, exportSchema = false)
public abstract class StudentRoomDB extends RoomDatabase {
    public abstract StudentDao getStudentDao();

    private static volatile StudentRoomDB INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecuter =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);


    static StudentRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentRoomDB.class) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        StudentRoomDB.class,
                        "student_databse"
                ).build();
            }
        }
        return INSTANCE;
    }

}
