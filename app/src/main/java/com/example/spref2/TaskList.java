package com.example.spref2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


public class TaskList extends ListFragment {


    MyApplication application;
    ArrayList<Task> tasks;
    public TaskList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tasks = new ArrayList<>();

        application = (MyApplication) requireActivity().getApplication();

        // Retrieve tasks from the application instance (if existing)
        tasks.addAll(application.getTasks());

        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            StringBuilder taskStringBuilder = new StringBuilder();
            taskStringBuilder.append(task.getTaskTitle()).append("\n");

            taskStrings.add(taskStringBuilder.toString());
        }

        if (!taskStrings.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, taskStrings); // Use a standard layout
            setListAdapter(adapter);  // Use setListAdapter to set the adapter for the ListView
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }
}