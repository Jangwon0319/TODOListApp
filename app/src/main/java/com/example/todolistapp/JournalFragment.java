package com.example.todolistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class JournalFragment extends Fragment {
    private RecyclerView recyclerView;
    private JournalAdapter adapter;
    private List<JournalItem> journalList = new ArrayList<>();
    private EditText editTextJournalTitle;
    private EditText editTextJournalContent;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        editTextJournalTitle = view.findViewById(R.id.edit_text_journal_title);
        editTextJournalContent = view.findViewById(R.id.edit_text_journal_content);
        saveButton = view.findViewById(R.id.button_save);
        recyclerView = view.findViewById(R.id.recycler_view_journal);

        this.adapter = new JournalAdapter(journalList, getContext());
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        saveButton.setOnClickListener(v -> {
            String title = editTextJournalTitle.getText().toString().trim();
            String content = editTextJournalContent.getText().toString().trim();

            if (!title.isEmpty() && title.length() <= 50 && !content.isEmpty() && content.length() <= 500) {
                JournalItem newJournal = new JournalItem(title, content);
                journalList.add(newJournal);
                this.adapter.notifyItemInserted(journalList.size() - 1);
                editTextJournalTitle.setText("");
                editTextJournalContent.setText("");
                Toast.makeText(getContext(), "Journal saved", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
