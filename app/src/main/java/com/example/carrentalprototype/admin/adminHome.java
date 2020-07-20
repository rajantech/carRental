package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrentalprototype.MainActivity;
import com.example.carrentalprototype.R;

public class adminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        setTitle("Admin Dashboard");



    }

    public void loadBookingList(View view) {
       // Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(adminHome.this, adminBookingList.class);
        startActivity(i);
    }




    public void loadAdminVehicleList(View view) {
        Intent i = new Intent(adminHome.this, admin_vehicle_list.class);
        startActivity(i);
    }

    public void loadAdminQueryList(View view) {
        Intent i = new Intent(adminHome.this, admin_query_list.class);
        startActivity(i);
    }


    public void loadAdminUserList(View view) {
        Intent i = new Intent(adminHome.this, admin_user_list.class);
        startActivity(i);
    }

    public void loadAdminAddVehicle(View view) {
        Intent i = new Intent(adminHome.this,adminAddNewVehicle.class);
        startActivity(i);
    }

    public void loadAdminAccount(View view) {
        Intent i = new Intent(adminHome.this,adminSettings.class);
        startActivity(i);
    }
}
