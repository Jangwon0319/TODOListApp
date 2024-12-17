package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private List<TodoItem> todoList = new ArrayList<>();
    private List<HistoryItem> historyList = new ArrayList<>();
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button addButton;
    private Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_todo);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        addButton = findViewById(R.id.button_add);
        historyButton = findViewById(R.id.button_history);

        adapter = new TodoAdapter(todoList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            if (!title.isEmpty() && !description.isEmpty()) {
                TodoItem newItem = new TodoItem(title, description, false);
                todoList.add(newItem);
                historyList.add(new HistoryItem(title, "Added", getCurrentTimestamp(), newItem));
                adapter.notifyDataSetChanged();
                editTextTitle.setText("");
                editTextDescription.setText("");
            }
        });

        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putExtra("historyList", new ArrayList<>(historyList));
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                if (data.getBooleanExtra("delete", false)) {
                    TodoItem deletedItem = todoList.get(position);
                    historyList.add(new HistoryItem(deletedItem.getTitle(), "Deleted", getCurrentTimestamp(), deletedItem));
                    todoList.remove(position);
                    adapter.notifyItemRemoved(position);
                } else {
                    String newTitle = data.getStringExtra("title");
                    String newDescription = data.getStringExtra("description");
                    TodoItem item = todoList.get(position);
                    item.setTitle(newTitle);
                    item.setDescription(newDescription);
                    historyList.add(new HistoryItem(newTitle, "Updated", getCurrentTimestamp(), item));
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
