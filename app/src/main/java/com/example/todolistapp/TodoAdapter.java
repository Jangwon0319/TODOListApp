package com.example.todolistapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<TodoItem> todoList;
    private Context context;

    public TodoAdapter(List<TodoItem> todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        TodoItem todo = todoList.get(position);
        holder.bind(todo);

        holder.completedCheckBox.setOnCheckedChangeListener(null);
        holder.completedCheckBox.setChecked(todo.isCompleted());
        holder.completedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.setCompleted(isChecked);
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TaskDetailActivity.class);
            intent.putExtra("title", todo.getTitle());
            intent.putExtra("description", todo.getDescription());
            intent.putExtra("position", position);
            ((Activity) context).startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView descriptionView;
        private CheckBox completedCheckBox;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.todo_title);
            descriptionView = itemView.findViewById(R.id.todo_description);
            completedCheckBox = itemView.findViewById(R.id.todo_checkbox);
        }

        public void bind(TodoItem todo) {
            titleView.setText(todo.getTitle());
            descriptionView.setText(todo.getDescription());
            completedCheckBox.setChecked(todo.isCompleted());
        }
    }
}