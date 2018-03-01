package com.example.a47.mya.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a47.mya.database.DatabaseContract;
import com.example.a47.mya.database.DbHelper;
import com.example.a47.mya.R;
import com.example.a47.mya.models.Task;
import com.example.a47.mya.ui.TaskDetailsActivity;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.Add_AdapterViewHolder> {

    private Context context;
    private ArrayList<Task> tasks;

    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super();
        this.tasks = tasks;
    }

    @Override
    public TasksAdapter.Add_AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_task_main, parent, false);
        Add_AdapterViewHolder add_save_db = new Add_AdapterViewHolder(itemView);
        return add_save_db;
    }

    @Override
    public void onBindViewHolder(TasksAdapter.Add_AdapterViewHolder holder, int position) {
        final Task text_save = tasks.get(position);
        holder.name_title.setText(text_save.getName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class Add_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView name_title;

        public Add_AdapterViewHolder(final View itemView) {
            super(itemView);

            name_title = (TextView) itemView.findViewById(R.id.name_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = tasks.get(getAdapterPosition());
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, TaskDetailsActivity.class);
                    intent.putExtra("ID", task.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
