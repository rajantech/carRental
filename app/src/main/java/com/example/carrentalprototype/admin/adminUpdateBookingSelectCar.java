package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.carrentalprototype.R;

public class adminUpdateBookingSelectCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_booking_select_car);

        setTitle("Update Booking");
    }

    public void loadBookingDetails(View view) {
    }
}
