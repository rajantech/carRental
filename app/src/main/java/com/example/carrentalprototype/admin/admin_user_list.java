package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carrentalprototype.R;

public class admin_user_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);
        getSupportActionBar().setTitle("User List");
    }


    public void loadAdminUserDetails(View view) {

            Intent i = new Intent(getApplicationContext(), adminViewUser.class);
            startActivity(i);

        }

}
