package com.pawanjeswani.todoapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pawanjeswani.todoapp.model.dbtables.NoteData;


@Database(entities = {NoteData.class} ,version = AppDatabase.DATABASE_VERSION, exportSchema = false)
//@TypeConverters({IntArrayConverter.class, StringArrayConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "Todo.db";

    public abstract TodoDao getTodoDao();

}