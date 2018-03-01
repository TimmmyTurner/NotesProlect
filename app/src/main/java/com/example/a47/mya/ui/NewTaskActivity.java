package com.example.a47.mya.ui;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.a47.mya.adapters.TasksAdapter;
import com.example.a47.mya.database.DatabaseContract;
import com.example.a47.mya.database.DbAdapter;
import com.example.a47.mya.database.DbHelper;
import com.example.a47.mya.R;
import com.example.a47.mya.database.TaskDatabaseAdapter;
import com.example.a47.mya.models.Task;


public class NewTaskActivity extends AppCompatActivity {

    DbAdapter dbAdapter;
    TaskDatabaseAdapter taskDatabaseAdapter;


    Task task;

    EditText title;
    EditText text;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title = (EditText) findViewById(R.id.title);
        text = (EditText) findViewById(R.id.text);

        taskDatabaseAdapter = new TaskDatabaseAdapter(this);
        taskDatabaseAdapter.open();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().equals("") || text.getText().toString().equals("")) {
                    Snackbar.make(view, "Введите данные", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    task = new Task();
                    task.setName(title.getText().toString());
                    task.setText(text.getText().toString());
                    taskDatabaseAdapter.insert(task);

                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        taskDatabaseAdapter.close();
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, NewTaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }
}
