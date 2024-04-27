package com.example.spref2;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {

    private static MyApplication instance;
    public static ArrayList<Task> tasks;
    @Override
    public void onCreate() {
        super.onCreate();
        tasks = new ArrayList<>();
        // Add some dummy tasks (replace with your actual data structure and values)
        tasks.add(new Task("Grocery Shopping", "Pick up milk", "2024-4-17", 1, false));
        tasks.add(new Task("Grocery Shopping", "Pick up milk", "2024-4-17", 2, false));
        tasks.add(new Task("Take a walk", "eat smth idk", "2024-4-17", 1, true));
        tasks.add(new Task("Grocery Shopping", "Pick up milk", "2024-4-17", 1, false));
        tasks.add(new Task("Grocery Shopping", "Pick up milk", "2024-4-17", 1, false));
        tasks.add(new Task("Grocery Shopping", "Pick up milk", "2024-4-17", 1, false));

    }
    public static MyApplication getInstance() {
        return instance;
    }

    public int getTaskIndex(Task targetTask) {
        return tasks.indexOf(targetTask);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTaskByIndex(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null; // Return null if index is out of bounds
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getTasksAsString() {
        StringBuilder tasksStringBuilder = new StringBuilder();
        if (tasks.isEmpty()) {
            tasksStringBuilder.append("No tasks added yet.");
        } else {
            for (Task task : tasks) {
                tasksStringBuilder.append("Task Title: ").append(task.getTaskTitle()).append("\n")
                        .append("Task Description: ").append(task.getTaskDesc()).append("\n")
                        .append("Priority: ").append(task.getPriority()).append("\n\n").append("Due date: ").append(task.getDueDate()).append("\n\n");
            }
        }
        return tasksStringBuilder.toString();
    }
}
