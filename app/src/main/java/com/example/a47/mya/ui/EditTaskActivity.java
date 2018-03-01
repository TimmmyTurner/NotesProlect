package com.example.a47.mya.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import com.example.a47.mya.database.DatabaseContract;
import com.example.a47.mya.database.DbAdapter;
import com.example.a47.mya.database.DbHelper;
import com.example.a47.mya.R;
import com.example.a47.mya.database.TaskDatabaseAdapter;
import com.example.a47.mya.models.Task;

public class EditTaskActivity extends AppCompatActivity {

    EditText title_name, text_text;
    Task task;
    long id;
    TaskDatabaseAdapter taskDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        id = getIntent().getLongExtra("ID", 0);
        title_name = (EditText) findViewById(R.id.title_name);
        text_text = (EditText) findViewById(R.id.text_text);

        taskDatabaseAdapter = new TaskDatabaseAdapter(this);
        taskDatabaseAdapter.open();
        task =  taskDatabaseAdapter.get(id);

        title_name.setText(task.getName());
        text_text.setText(task.getText());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title_name.getText().toString().equals("") || text_text.getText().toString().equals("")) {
                    Snackbar.make(view, "Вы не изменили данные", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    task.setName(title_name.getText().toString());
                    task.setText(text_text.getText().toString());
                    taskDatabaseAdapter.update(task);

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}
