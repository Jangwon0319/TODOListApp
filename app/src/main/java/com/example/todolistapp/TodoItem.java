package com.example.todolistapp;

/* ================================ */
/*         TodoItem.java             */
/* ================================ */

import java.io.Serializable;

public class TodoItem implements Serializable {
    private String title;
    private String description;
    private boolean isCompleted;
    private String category;

    public TodoItem(String title, String description, boolean isCompleted, String category) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}