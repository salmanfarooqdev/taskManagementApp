package com.example.spref2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {


    TextInputEditText etTitle, etDesc;
    EditText dueDate;
    ImageButton dueDateBtn;
    Spinner dropdown;
    Button addTaskBtn;

    MyApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);

        init();

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String desc = intent.getStringExtra("desc");
            String dueDat = intent.getStringExtra("due");
            int priority = intent.getIntExtra("priority", 1); // Default priority if not found

            // Pre-fill input fields with task data
            etTitle.setText(title);
            etDesc.setText(desc);
            dueDate.setText(dueDat);

        }

        dueDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        dueDate.setText(year + "-"+ (month+1) + "-"+ dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String due = dueDate.getText().toString().trim();
                int priority = Integer.parseInt( dropdown.getSelectedItem().toString());

                Task task = new Task(title,desc, due, priority, false);
                application.addTask(task);

                etTitle.setText("");
                etDesc.setText("");
                dueDate.setText("");
                Toast.makeText(AddTask.this, "Task Added Succesfully!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(AddTask.this, Home.class));
            }
        });

    }

    private void init()
    {
        getSupportActionBar().setTitle("Add Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dueDate = findViewById(R.id.etDate);
        dueDateBtn = findViewById(R.id.etDateBtn);
        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"1", "2", "3", "4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        addTaskBtn = findViewById(R.id.addTaskBtn);
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);

        application = (MyApplication) getApplicationContext();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}