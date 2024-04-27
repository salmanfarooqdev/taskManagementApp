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

    }
    public static MyApplication getInstance() {
        return instance;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
