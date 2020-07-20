package com.example.carrentalprototype;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class clientForgetPasswordCode extends AppCompatActivity {
TextView code;
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_forget_password_code);
        code = findViewById(R.id.pass);
        email =getIntent().getStringExtra("email");


        int max = 9;
        int min = 1;
        int range = max - min + 1;

        int rand1 = (int)(Math.random() * range) + min;
        int rand2 = (int)(Math.random() * range) + min;
        int rand3 = (int)(Math.random() * range) + min;
        int rand4 = (int)(Math.random() * range) + min;
/*
        try {
            TimeUnit.SECONDS.sleep(180);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        code.setText(rand1+" "+ rand2 + " " + rand3+" " + rand4);
    }

    public void submit(View view) {
        Intent intent=new Intent(getApplicationContext(), clientSetNewPassword.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}
