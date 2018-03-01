package com.example.a47.mya.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;;

import com.example.a47.mya.models.Task;

import java.util.ArrayList;

// Этот класс реализует TaskDao
public class TaskDatabaseAdapter extends DbAdapter implements TaskDao {
    private static final String TAG = TaskDatabaseAdapter.class.getSimpleName();

    private Context context;

    public TaskDatabaseAdapter(Context context) {
        super(context);
    }

    @Override
    public long insert(Task task) {
        ContentValues tasks = createContentValues(task);
        return sqLiteDatabase.insert(DatabaseContract.TaskEntry.TABLE_NAME, null, tasks);
    }

    @Override
    public int update(Task task) {
        ContentValues values = createContentValues(task);
        return sqLiteDatabase.update(DatabaseContract.TaskEntry.TABLE_NAME,
                values,
                DatabaseContract.TaskEntry._ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    @Override
    public  int delete(Task task) {
        return sqLiteDatabase.delete(DatabaseContract.TaskEntry.TABLE_NAME,
                DatabaseContract.TaskEntry._ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    @Override
    public void deleteAll() {
        sqLiteDatabase.delete(DatabaseContract.TaskEntry.TABLE_NAME,null,null);
    }

    @Override
    public ArrayList<Task> getAll() {
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(DatabaseContract.TaskEntry.TABLE_NAME, new String[]{
                DatabaseContract.TaskEntry._ID,
                DatabaseContract.TaskEntry.NAME,
                DatabaseContract.TaskEntry.TEXT,
        }, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int IndexId = cursor.getColumnIndex(DatabaseContract.TaskEntry._ID);
            int IndexName = cursor.getColumnIndex(DatabaseContract.TaskEntry.NAME);
            int IndexText = cursor.getColumnIndex(DatabaseContract.TaskEntry.TEXT);
            do {
                Task task = new Task();
                Log.e("myLog", " ID = " + cursor.getInt(IndexId) +
                        " name = " + cursor.getString(IndexName) +
                        " text =  " + cursor.getString(IndexText));
                task.setId(cursor.getLong(IndexId));
                task.setName(cursor.getString(IndexName));
                task.setText(cursor.getString(IndexText));
                tasks.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return tasks;
    }

    @Override
    public Task get(long id) {
        Task task = null;
        Cursor cursor = sqLiteDatabase.query(true, DatabaseContract.TaskEntry.TABLE_NAME,
                new String[]{DatabaseContract.TaskEntry._ID,
                        DatabaseContract.TaskEntry.NAME,
                        DatabaseContract.TaskEntry.TEXT},
                DatabaseContract.TaskEntry._ID + " = " + id, null, null, null, null, null);
        try {
            cursor.moveToFirst();
            task = new Task();
            task.setId(cursor.getLong(cursor.getColumnIndex(DatabaseContract.TaskEntry._ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.TaskEntry.NAME)));
            task.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.TaskEntry.TEXT)));
        } catch (Exception e) {
            Log.e(TAG, "Could not get the record");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return task;
    }

    public static ContentValues createContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TaskEntry.NAME, task.getName());
        values.put(DatabaseContract.TaskEntry.TEXT, task.getText());
        return values;
    }

}
