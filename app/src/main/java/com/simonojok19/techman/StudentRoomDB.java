package com.simonojok19.techman;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Student.class }, version = 1, exportSchema = false)
public abstract class StudentRoomDB extends RoomDatabase {
}
