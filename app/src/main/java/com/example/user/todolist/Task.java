package com.example.user.todolist;

import java.io.Serializable;

/**
 * Created by user on 08/07/2017.
 */

public class Task implements Serializable {

    private String title;
    private String description;
    private boolean isDone;
    private boolean isDeleted;

    public  Task(String title, String description, boolean isDone, boolean isDeleted) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.isDeleted = isDeleted;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCompleted() {
        this.isDone = true;
    }

    public void setDeleted() {
        this.isDeleted = true;
    }

    public boolean getCompletedStatus() {
        return this.isDone;
    }

    public boolean getDeletedStatus() {
        return this.isDeleted;
    }






}
