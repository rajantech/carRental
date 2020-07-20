package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carrentalprototype.R;

public class adminProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        setTitle("My Information");
    }
}
