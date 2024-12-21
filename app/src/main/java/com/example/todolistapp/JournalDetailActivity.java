package com.example.todolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JournalDetailActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editContent;
    private TextView detailTitle;
    private TextView detailContent;
    private Button updateButton;
    private Button deleteButton;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_detail);

        detailTitle = findViewById(R.id.detail_title);
        detailContent = findViewById(R.id.detail_content);
        editTitle = findViewById(R.id.detail_edit_title);
        editContent = findViewById(R.id.detail_edit_content);
        updateButton = findViewById(R.id.button_update);
        deleteButton = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        position = intent.getIntExtra("position", -1);

        detailTitle.setText(title);
        detailContent.setText(content);
        editTitle.setText(title);
        editContent.setText(content);

        // 수정 버튼 클릭
        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString().trim();
            String newContent = editContent.getText().toString().trim();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("content", newContent);
            resultIntent.putExtra("position", position);
            resultIntent.putExtra("type", "JOURNAL"); // 구분을 위해 JOURNAL 타입 추가
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        // 삭제 버튼 클릭
        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("delete", true);
            resultIntent.putExtra("position", position);
            resultIntent.putExtra("type", "JOURNAL"); // 구분을 위해 JOURNAL 타입 추가
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
