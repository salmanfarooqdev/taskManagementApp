package com.example.spref2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Setting extends AppCompatActivity {

    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
       getSupportActionBar().setTitle("Settings");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       btnLogout = findViewById(R.id.btnLogout);
       btnLogout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences sPref = getSharedPreferences(MainActivity.FILENAME, MODE_PRIVATE);
               SharedPreferences.Editor editor = sPref.edit();
               //editor.clear();
              // editor.remove("key_username");
              // editor.remove("key_password");
               editor.putBoolean("isLogin", false);
               editor.apply();

               startActivity(new Intent(Setting.this, MainActivity.class));
               finish();
           }
       });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}