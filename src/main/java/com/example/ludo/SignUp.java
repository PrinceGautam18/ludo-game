package com.example.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    TextView loginbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginbtn=findViewById(R.id.login);
        Intent loginBack=new Intent(this, com.example.ludo.LoginPage.class);
        startActivity(loginBack);
        finish();


    }

    public void skip(View view) {
        Intent inext=new Intent(this, com.example.ludo.MainActivity.class);
        startActivity(inext);
        finish();
    }
}