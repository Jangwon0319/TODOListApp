package com.example.todolistapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {
    private List<JournalItem> journalList;
    private Context context;

    public JournalAdapter(List<JournalItem> journalList, Context context) {
        this.journalList = journalList;
        this.context = context;
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {
        JournalItem journal = journalList.get(position);
        holder.bind(journal);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, JournalDetailActivity.class);
            intent.putExtra("title", journal.getTitle());
            intent.putExtra("content", journal.getContent());
            intent.putExtra("position", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public static class JournalViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView, contentView;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.journal_title);
            contentView = itemView.findViewById(R.id.journal_content);
        }

        public void bind(JournalItem journal) {
            titleView.setText(journal.getTitle());
            contentView.setText(journal.getContent());
        }
    }
}

