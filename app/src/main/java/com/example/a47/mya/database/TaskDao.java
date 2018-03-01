package com.example.a47.mya.database;


import com.example.a47.mya.models.Task;

import java.util.ArrayList;

public interface TaskDao {
    long insert(Task task);

    int update(Task task);

    int delete(Task task);

    void deleteAll();

    ArrayList<Task> getAll();

    Task get(long id);
}
