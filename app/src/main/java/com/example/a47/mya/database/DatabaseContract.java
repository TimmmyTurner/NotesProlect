package com.example.a47.mya.database;

import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final String SQL_CREATE_WORD_ENTRIES =
            "CREATE TABLE " + TaskEntry.TABLE_NAME + " ("
                    + TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TaskEntry.NAME + " TEXT, "
                    + TaskEntry.TEXT + " TEXT);";

    private DatabaseContract() {
    }

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String NAME = "title";
        public static final String TEXT = "description";
    }
}