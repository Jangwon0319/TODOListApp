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
        EditText editTitle = findViewById(R.id.detail_edit_title);
        EditText editDescription = findViewById(R.id.detail_edit_description);
        Button updateButton = findViewById(R.id.button_update);
        Button deleteButton = findViewById(R.id.button_delete);

        // 받는 데이터
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        position = intent.getIntExtra("position", -1);

        // 기존 데이터를 UI에 반영
        detailTitle.setText(title); // 기존 제목
        detailDescription.setText(description); // 기존 설명
        editTitle.setText(title); // 제목 수정 가능
        editDescription.setText(description); // 설명 수정 가능

        // 수정 버튼 클릭 이벤트
        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString();
            String newDescription = editDescription.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("description", newDescription);
            resultIntent.putExtra("position", position); // 변경된 위치 정보
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        // 삭제 버튼 클릭 이벤트
        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("delete", true); // 삭제 플래그 설정
            resultIntent.putExtra("position", position);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
