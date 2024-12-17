package com.example.todolistapp;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private TextView titleView;
    private TextView descriptionView;
    private CheckBox completedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        titleView = findViewById(R.id.detail_title);
        descriptionView = findViewById(R.id.detail_description);
        completedCheckBox = findViewById(R.id.detail_checkbox);

        titleView.setText("Example Task");
        descriptionView.setText("This is a detailed description of the task.");
    }
}