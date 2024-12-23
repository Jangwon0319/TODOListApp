package com.example.todolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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

        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailDescription = findViewById(R.id.detail_description);
        editTitle = findViewById(R.id.detail_edit_title);
        editDescription = findViewById(R.id.detail_edit_description);
        updateButton = findViewById(R.id.button_update);
        deleteButton = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        position = intent.getIntExtra("position", -1);

        detailTitle.setText(title);
        detailDescription.setText(description);
        editTitle.setText(title);
        editDescription.setText(description);

        // 수정
        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString();
            String newDescription = editDescription.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("description", newDescription);
            resultIntent.putExtra("position", position);
            resultIntent.putExtra("type", "TODO");
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        // 삭제
        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("delete", true);
            resultIntent.putExtra("position", position);
            resultIntent.putExtra("type", "TODO");
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
