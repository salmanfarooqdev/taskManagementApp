package com.example.spref2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


public class TaskList extends ListFragment {


    public interface ItemSelected
    {
        public void onItemClicked(int index);
    }

    MyApplication application;
    ArrayList<Task> tasks;
    ListView listView;
    ItemSelected myActivity;
    ArrayAdapter<String> adapter;
    public TaskList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        myActivity = (ItemSelected) context;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        myActivity.onItemClicked(position);
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
           adapter = new ArrayAdapter<>(requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, taskStrings); // Use a standard layout
            setListAdapter(adapter);

            Log.d("TaskList", "Adapter initialized. Task count: " + tasks.size());

        }

    }

    public void updateAdapterData(ArrayList<Task> updatedTasks) {
        adapter.clear();

        // Add updated data
        for (Task task : updatedTasks) {
            StringBuilder taskStringBuilder = new StringBuilder();
            taskStringBuilder.append(task.getTaskTitle()).append("\n");
            adapter.add(taskStringBuilder.toString());
        }

        // Notify adapter of data change
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }
}