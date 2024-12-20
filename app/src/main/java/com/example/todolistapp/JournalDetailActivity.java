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
    private TextView detailTitle;  // 기존 제목 표시
    private TextView detailContent; // 기존 설명 표시
    private Button updateButton;
    private Button deleteButton;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_detail);

        // 뷰 초기화
        editTitle = findViewById(R.id.detail_edit_title);
        editContent = findViewById(R.id.detail_edit_content);
        detailTitle = findViewById(R.id.detail_title); // 기존 제목 표시
        detailContent = findViewById(R.id.detail_content); // 기존 설명 표시
        updateButton = findViewById(R.id.button_update);
        deleteButton = findViewById(R.id.button_delete);

        // 인텐트로 전달받은 데이터 가져오기
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        position = intent.getIntExtra("position", -1);

        // 기존 제목, 설명을 TextView에 설정
        detailTitle.setText(title); // 기존 제목 표시
        detailContent.setText(content); // 기존 설명 표시

        // EditText에 기존 값 설정 (수정 가능)
        editTitle.setText(title);
        editContent.setText(content);

        // 수정 버튼 클릭 이벤트
        updateButton.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString().trim();
            String newContent = editContent.getText().toString().trim();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", newTitle);
            resultIntent.putExtra("content", newContent);
            resultIntent.putExtra("position", position);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        // 삭제 버튼 클릭 이벤트
        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("delete", true);
            resultIntent.putExtra("position", position);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}