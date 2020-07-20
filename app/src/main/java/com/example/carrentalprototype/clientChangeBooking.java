package com.example.carrentalprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class clientChangeBooking extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_change_booking);

        setTitle("Change Booking Information");

        textView = findViewById(R.id.continu);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(), "Continue", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), clientVehicleList.class);
                startActivity(i);
            }
        });
    }
}
