package com.example.spref2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Home extends AppCompatActivity implements TaskList.ItemSelected{

    ImageView settingIcon;
    TextView taskDisplay, tvTask, tvDesc, tvDue;

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
//       String taskss =  application.getTasksAsString();
//       taskDisplay.setText(taskss);

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
                        task = null;


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
        checkbox = v.findViewById(R.id.checkbox);


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

        task = MyApplication.tasks.get(index);

        tvTask.setText(task.getTaskTitle());
        tvDesc.setText(task.getTaskDesc());
        tvDue.setText(task.getDueDate());
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
}