package com.example.a47.mya.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DbAdapter {
    private Context mContext;
    private DbHelper mDbHelper;
    protected SQLiteDatabase sqLiteDatabase;

    public DbAdapter(Context context) {
        mContext = context;
    }

    public void open() throws SQLException {
        mDbHelper = new DbHelper(mContext);
        sqLiteDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }
}
