package com.example.spref2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Home extends AppCompatActivity implements TaskList.ItemSelected, TaskList.OnButtonClickListener{

    ImageView settingIcon;
    TextView taskDisplay, tvTask, tvDesc, tvDue, tvPriority;

    CheckBox checkbox;
    Button deleteBtn;
    Task task;


    FragmentManager manager;
    Fragment taskList, taskDetail;
    MyApplication application;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        init();

MyApplication application = (MyApplication) getApplicationContext();


        if(findViewById(R.id.layout_portrait) != null)
        {
            manager.beginTransaction()
                    .show(taskList)
                    .hide(taskDetail)
                    .commit();
        }
        if(findViewById(R.id.layout_landscape) != null)
        {
            manager.beginTransaction()
                    .show(taskList)
                    .show(taskDetail)
                    .commit();
        }

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task != null) {

                        MyApplication.tasks.remove(task);

                    ArrayList<Task> updatedTasks = MyApplication.tasks;

                    // Update TaskList fragment
                    TaskList taskListFragment = (TaskList) taskList;
                    if (taskListFragment != null) {
                        taskListFragment.updateAdapterData(updatedTasks);
                    }

                        Toast.makeText(Home.this, "Task deleted successfully!", Toast.LENGTH_SHORT).show();
                        //task = null;


                        if (findViewById(R.id.layout_portrait) != null) {
                            manager.beginTransaction()
                                    .show(taskList)
                                    .hide(taskDetail)
                                    .commit();
                        }
                    } else {

                        Toast.makeText(Home.this, "Error deleting task!", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (task != null) {
                    task.setCompleted(isChecked);
                    Toast.makeText(Home.this, "Task completed: " + isChecked, Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    private void init()
    {
        getSupportActionBar().setTitle("To Do List");
        manager = getSupportFragmentManager();

        taskList = manager.findFragmentById(R.id.listfrag);
        taskDetail = manager.findFragmentById(R.id.detailfrag);
        deleteBtn = findViewById(R.id.deleteBtn);


        assert taskDetail != null;
        v = taskDetail.requireView();

        MyApplication application = (MyApplication) getApplicationContext();


        tvTask = v.findViewById(R.id.tvTask);
        tvDesc = v.findViewById(R.id.tvDesc);
        tvDue = v.findViewById(R.id.tvDue);
        tvPriority = v.findViewById(R.id.tvPriority);
        checkbox = v.findViewById(R.id.checkbox);

        if (!MyApplication.tasks.isEmpty()) {
            task = MyApplication.tasks.get(0);
            tvTask.setText(task.getTaskTitle());
            tvDesc.setText(task.getTaskDesc());
            tvDue.setText(task.getDueDate());
            tvPriority.setText(String.valueOf(task.getPriority()));
            checkbox.setChecked(task.isCompleted());
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settingsItem) {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
            return true;

        }else if (id == R.id.addItem) {
            Intent intent = new Intent(this, AddTask.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int index) {

        String message = "Clicked position: " + index;
        Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
        task = MyApplication.tasks.get(index);

        tvTask.setText(task.getTaskTitle());
        tvDesc.setText(task.getTaskDesc());
        tvDue.setText(task.getDueDate());
        tvPriority.setText(String.valueOf(task.getPriority()));
        checkbox.setChecked(task.isCompleted());

        if(findViewById(R.id.layout_portrait) != null)
        {
            manager.beginTransaction()
                    .hide(taskList)
                    .show(taskDetail)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onAllButtonClick() {

        if(task != null) {

            Collections.sort(MyApplication.tasks);

            // Update TaskList fragment with the sorted list of tasks
            TaskList taskListFragment = (TaskList) taskList;
            if (taskListFragment != null) {
                taskListFragment.updateAdapterData(MyApplication.tasks);
            }

        }
    }

    @Override
    public void onCompletedButtonClick() {

        if(task != null) {

            ArrayList<Task> completedTasks = new ArrayList<>();

            // Filter completed tasks
            for (Task task : MyApplication.tasks) {
                if (task.isCompleted()) {
                    completedTasks.add(task);
                }
            }

            // Update TaskList fragment
            TaskList taskListFragment = (TaskList) taskList;
            if (taskListFragment != null) {
                taskListFragment.updateAdapterData(completedTasks);
            }

        }

    }

    @Override
    public void onPriorityButtonClick() {

        if(task != null) {
            Collections.sort(MyApplication.tasks, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    return Integer.compare(task1.getPriority(), task2.getPriority());
                }
            });

            // Update TaskList fragment with the sorted list of tasks
            TaskList taskListFragment = (TaskList) taskList;
            if (taskListFragment != null) {
                taskListFragment.updateAdapterData(MyApplication.tasks);
            }

        }

    }
}