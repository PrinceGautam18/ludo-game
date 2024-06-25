package com.example.ludo;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    TextView signupbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //  signupbtn=findViewById(R.id.signup);





    }

    public void skip(View view) {
        Intent inext=new Intent(this, com.example.ludo.MainActivity.class);
        startActivity(inext);
        finish();
    }

    public void signup(View view) {
        Intent signupNext=new Intent(this, com.example.ludo.SignUp.class);
        startActivity(signupNext);

    }
}