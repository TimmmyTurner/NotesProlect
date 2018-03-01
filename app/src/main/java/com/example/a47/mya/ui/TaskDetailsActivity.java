package com.example.a47.mya.ui;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.a47.mya.database.DbAdapter;
import com.example.a47.mya.database.DbHelper;
import com.example.a47.mya.database.DatabaseContract;
import com.example.a47.mya.R;
import com.example.a47.mya.database.TaskDatabaseAdapter;
import com.example.a47.mya.models.Task;

import java.util.ArrayList;

public class TaskDetailsActivity extends AppCompatActivity{

    Task task;
    TextView name;
    TextView text;
    TaskDatabaseAdapter taskDatabaseAdapter;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_defails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getLongExtra("ID",0);
        taskDatabaseAdapter = new TaskDatabaseAdapter(this);
        name = (TextView) findViewById(R.id.title);
        text = (TextView) findViewById(R.id.text);
        taskDatabaseAdapter.open();
        task = taskDatabaseAdapter.get(id);

        name.setText(task.getName());
        text.setText(task.getText());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditTaskActivity.class);
                intent.putExtra("ID", id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        taskDatabaseAdapter.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_defails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ide = item.getItemId();
        if (ide == R.id.delete_task) {
            taskDatabaseAdapter.delete(task);
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
