package com.example.spref2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    ImageView settingIcon;
    TextView taskDisplay;



    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        init();

//        MyApplication application = (MyApplication) getApplicationContext();
//       String taskss =  application.getTasksAsString();
//       taskDisplay.setText(taskss);




    }

    private void init()
    {
        getSupportActionBar().setTitle("To Do List");

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
}