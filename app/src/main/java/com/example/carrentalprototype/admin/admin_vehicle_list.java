package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carrentalprototype.R;

public class admin_vehicle_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vehicle_list);
        getSupportActionBar().setTitle("Vehicle List");
    }

    public void loadAdminVehicleDetails(View view) {


            Intent i = new Intent(getApplicationContext(), adminViewVehicle.class);
            startActivity(i);


    }
}
