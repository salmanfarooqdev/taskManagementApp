package com.example.spref2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "credentials";

    FragmentManager manager;
    Fragment loginFrag, signupFrag;
    View loginView, signupView;

    TextView tvLogin, tvSignUp;

    TextInputEditText etusernameS, etPasswordS, etCPasswordS;
    Button btnSignup, btnCancelS;

    TextInputEditText etusernameL, etPasswordL;
    Button btnLogin, btnCancelL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        init();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .hide(loginFrag)
                        .show(signupFrag)
                        .commit();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .hide(signupFrag)
                        .show(loginFrag)
                        .commit();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etusernameS.getText().toString().trim();
                String password = etPasswordS.getText().toString();
                String cPassword = etCPasswordS.getText().toString();

                if(username.isEmpty() || password.isEmpty() || cPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Field is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.equals(cPassword))
                    {
                        SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sPref.edit();
                        editor.putString("key_username", username);
                        editor.putString("key_password", password);

                        editor.apply();

                        manager.beginTransaction()
                                .show(loginFrag)
                                .hide(signupFrag)
                                .commit();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Password Doesnt Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etusernameL.getText().toString().trim();
                String password = etPasswordL.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Field is Empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
                    String fusername = sPref.getString("key_username", "");
                    String fpassword = sPref.getString("key_password", "");

                    if(fusername.equals(username) && fpassword.equals(password))
                    {
                        SharedPreferences.Editor editor = sPref.edit();
                        editor.putBoolean("isLogin", true);
                        editor.apply();

                        startActivity(new Intent(MainActivity.this, Home.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Doesnt match", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            }
        });





    }

    private void init()
    {

        Objects.requireNonNull(getSupportActionBar()).hide();
        SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
        boolean flag = sPref.getBoolean("isLogin", false);
        if(flag)
        {
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();

        }

        manager = getSupportFragmentManager();
        loginFrag = manager.findFragmentById(R.id.loginfrag);
        signupFrag = manager.findFragmentById(R.id.signupfrag);

        loginView = loginFrag.getView();
        signupView = signupFrag.getView();

        assert signupView != null;
        tvSignUp = loginView.findViewById(R.id.tvSignup);
        tvLogin = signupView.findViewById(R.id.tvLogin);

        // sign up hooks
        etCPasswordS = signupView.findViewById(R.id.etCPassword);
        etPasswordS = signupView.findViewById(R.id.etPassword);
        etusernameS = signupView.findViewById(R.id.etUsername);
        btnSignup = signupView.findViewById(R.id.btnSignup);

        // login hooks

        etPasswordL = loginView.findViewById(R.id.etPassword);
        etusernameL = loginView.findViewById(R.id.etUsername);
        btnLogin = loginView.findViewById(R.id.btnLogin);

        manager.beginTransaction()
                .hide(signupFrag)
                .commit();
    }

}
