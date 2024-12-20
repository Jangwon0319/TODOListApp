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

public class JournalFragment extends Fragment {
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

        saveButton.setOnClickListener(v -> {
            String title = editTextJournalTitle.getText().toString();
            String content = editTextJournalContent.getText().toString();

            if (!title.isEmpty() && !content.isEmpty()) {
                editTextJournalTitle.setText("");
                editTextJournalContent.setText("");
                Toast.makeText(getContext(), "Journal saved", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}


