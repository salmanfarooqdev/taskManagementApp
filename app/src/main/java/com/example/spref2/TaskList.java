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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


public class TaskList extends ListFragment {


    public interface ItemSelected
    {
        public void onItemClicked(int index);
    }

    public interface OnButtonClickListener {
        void onAllButtonClick();
        void onCompletedButtonClick();
        void onPriorityButtonClick();
    }

    Button btnAll, btnCompleted, btnPriority;
    OnButtonClickListener mListener;
    MyApplication application;
    ArrayList<Task> tasks;
    ListView listView;
    ItemSelected myActivity;
    ArrayAdapter<String> adapter;
    public TaskList() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (ItemSelected) context;

        try {
            mListener = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnButtonClickListener");
        }
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
        tasks.addAll(application.getTasks());

        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            StringBuilder taskStringBuilder = new StringBuilder();
            taskStringBuilder.append(task.getTaskTitle()).append("\n");

            taskStrings.add(taskStringBuilder.toString());
        }

        if (!taskStrings.isEmpty()) {
           adapter = new ArrayAdapter<>(requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, taskStrings);
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

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        // Initialize buttons
        btnAll = view.findViewById(R.id.btn_all);
        btnCompleted = view.findViewById(R.id.btn_completed);
        btnPriority = view.findViewById(R.id.btn_priority);

        // Set click listeners for each button
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAllButtonClick();
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCompletedButtonClick();
            }
        });

        btnPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPriorityButtonClick();
            }
        });

        return view;


    }
}