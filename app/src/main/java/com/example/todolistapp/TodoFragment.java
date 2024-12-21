package com.example.todolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoFragment extends Fragment {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private List<TodoItem> todoList = new ArrayList<>();
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_todo);
        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextDescription = view.findViewById(R.id.edit_text_description);
        addButton = view.findViewById(R.id.button_add);

        this.adapter = new TodoAdapter(todoList, getContext());
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 할 일 추가
        addButton.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (!title.isEmpty() && title.length() <= 50 && !description.isEmpty() && description.length() <= 200) {
                TodoItem newItem = new TodoItem(title, description, false);
                todoList.add(newItem);
                this.adapter.notifyItemInserted(todoList.size() - 1);
                editTextTitle.setText("");
                editTextDescription.setText("");
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            if (position != -1 && position < todoList.size()) {
                if (data.getBooleanExtra("delete", false)) {
                    // 삭제 처리
                    todoList.remove(position);
                    adapter.notifyItemRemoved(position);
                } else {
                    // 수정 처리
                    String newTitle = data.getStringExtra("title");
                    String newDescription = data.getStringExtra("description");

                    TodoItem updatedItem = todoList.get(position);
                    updatedItem.setTitle(newTitle);
                    updatedItem.setDescription(newDescription);

                    adapter.notifyItemChanged(position);
                }
            }
        }
    }
}
