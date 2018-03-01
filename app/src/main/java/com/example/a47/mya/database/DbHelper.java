package com.example.a47.mya.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.a47.mya.R;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "app";
    public static final int VERSION = 1;

    private Context mContext;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion) {
        if (oldVersion < 1) {
            db.execSQL(DatabaseContract.SQL_CREATE_WORD_ENTRIES);
        }
    }
}
