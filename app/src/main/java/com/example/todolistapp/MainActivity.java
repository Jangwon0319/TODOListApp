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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private List<TodoItem> todoList = new ArrayList<>();
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_todo);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        addButton = findViewById(R.id.button_add);

        adapter = new TodoAdapter(todoList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            if (!title.isEmpty() && !description.isEmpty()) {
                todoList.add(new TodoItem(title, description, false));
                adapter.notifyDataSetChanged();
                editTextTitle.setText("");
                editTextDescription.setText("");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                if (data.getBooleanExtra("delete", false)) {
                    todoList.remove(position);
                    adapter.notifyItemRemoved(position);
                } else {
                    String newTitle = data.getStringExtra("title");
                    String newDescription = data.getStringExtra("description");
                    TodoItem item = todoList.get(position);
                    item.setTitle(newTitle);
                    item.setDescription(newDescription);
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }
}
