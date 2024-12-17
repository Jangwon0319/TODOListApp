package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDescription;
    private Button updateButton;
    private Button deleteButton;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTitle = findViewById(R.id.detail_edit_title);
        editDescription = findViewById(R.id.detail_edit_description);
        updateButton = findViewById(R.id.button_update);
        deleteButton = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        position = intent.getIntExtra("position", -1);

        editTitle.setText(title);
        editDescription.setText(description);

        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString();
            String newDescription = editDescription.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("description", newDescription);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("delete", true);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
