package com.example.todolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JournalDetailActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editContent;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_detail);

        editTitle = findViewById(R.id.detail_edit_title);
        editContent = findViewById(R.id.detail_edit_content);
        updateButton = findViewById(R.id.button_update);
        deleteButton = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        editTitle.setText(title);
        editContent.setText(content);

        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString();
            String newContent = editContent.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("content", newContent);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("delete", true);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
