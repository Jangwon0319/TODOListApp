package com.example.todolistapp;

import java.io.Serializable;

public class HistoryItem implements Serializable {
    private String taskName;
    private String action;
    private String timestamp;
    private TodoItem todoItem;

    public HistoryItem(String taskName, String action, String timestamp, TodoItem todoItem) {
        this.taskName = taskName;
        this.action = action;
        this.timestamp = timestamp;
        this.todoItem = todoItem;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getAction() {
        return action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }
}

