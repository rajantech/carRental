package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carrentalprototype.R;

public class adminSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        setTitle("Settings");
    }

    public void chngpass(View view) {
        Intent i = new Intent(getApplicationContext(), adminChangePassword.class);
        startActivity(i);

    }

    public void chngemail(View view) {
        Intent i = new Intent(getApplicationContext(), adminChangeEmail.class);
        startActivity(i);
    }

    public void myProfile(View view) {
        Intent i = new Intent(getApplicationContext(), adminProfile.class);
        startActivity(i);
    }

}
