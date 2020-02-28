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
                ).addCallback(callback).build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecuter.execute(() -> {
                StudentDao dao = INSTANCE.getStudentDao();
                dao.deleteStudents();
                Teacher teacher1 = new Teacher(
                        "Robert Kyagulanyi",
                        "Masters Degree",
                        "Makerere University",
                        "Wakiso District"
                );
                dao.insert(teacher1);
                Teacher teacher2 = new Teacher(
                        "Dr Kizza Besigy",
                        "Master Degree",
                        "Oxford University",
                        "Kampala District"
                );
                dao.insert(teacher2);

                Teacher teacher3 = new Teacher(
                        "Simon Peter Ojok",
                        "Undergraduate Degree",
                        "Makerere University",
                        "Gulu District"
                );
                dao.insert(teacher3);
            });
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
        }
    };
}
