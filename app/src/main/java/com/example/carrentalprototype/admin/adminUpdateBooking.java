package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.carrentalprototype.R;

public class adminUpdateBooking extends AppCompatActivity {
TextView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_booking);
        setTitle("Update Booking");



            btn = findViewById(R.id.continu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),adminUpdateBookingSelectCar.class);
                startActivity(i);
            }
        });
    }
}
