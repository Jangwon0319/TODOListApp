package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDescription;
    private Spinner spinnerCategory;
    private Button updateButton;
    private Button deleteButton;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTitle = findViewById(R.id.detail_edit_title);
        editDescription = findViewById(R.id.detail_edit_description);
        spinnerCategory = findViewById(R.id.spinner_detail_category);
        updateButton = findViewById(R.id.button_update);
        deleteButton = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String category = intent.getStringExtra("category");
        position = intent.getIntExtra("position", -1);

        editTitle.setText(title);
        editDescription.setText(description);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Set the current category in the Spinner
        if (category != null) {
            int spinnerPosition = categoryAdapter.getPosition(category);
            spinnerCategory.setSelection(spinnerPosition);
        }

        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString();
            String newDescription = editDescription.getText().toString();
            String newCategory = spinnerCategory.getSelectedItem().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("description", newDescription);
            resultIntent.putExtra("category", newCategory);
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
