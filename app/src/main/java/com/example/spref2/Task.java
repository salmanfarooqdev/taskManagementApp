package com.example.spref2;

import java.util.Date;

public class Task {

    String taskTitle;
    String taskDesc;
    String dueDate;
    int priority;
    boolean isCompleted;

    public Task() {

    }

    public Task(String taskTitle, String taskDesc, String dueDate, int priority, boolean isCompleted) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
