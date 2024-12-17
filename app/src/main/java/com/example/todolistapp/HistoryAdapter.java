package com.example.todolistapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryItem> historyList;
    private Context context;

    public HistoryAdapter(List<HistoryItem> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem historyItem = historyList.get(position);
        holder.bind(historyItem);

        if (!historyItem.getAction().equals("Deleted")) {
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, TaskDetailActivity.class);
                intent.putExtra("title", historyItem.getTodoItem().getTitle());
                intent.putExtra("description", historyItem.getTodoItem().getDescription());
                intent.putExtra("position", position);
                ((Activity) context).startActivityForResult(intent, 1);
            });
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView taskNameView;
        private TextView actionView;
        private TextView timestampView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameView = itemView.findViewById(R.id.history_task_name);
            actionView = itemView.findViewById(R.id.history_action);
            timestampView = itemView.findViewById(R.id.history_timestamp);
        }

        public void bind(HistoryItem historyItem) {
            taskNameView.setText(historyItem.getTaskName());
            actionView.setText(historyItem.getAction());
            timestampView.setText(historyItem.getTimestamp());
        }
    }
}