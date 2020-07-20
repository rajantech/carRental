package com.example.carrentalprototype.ui.home;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.carrentalprototype.R;
import com.example.carrentalprototype.*;
import  com.example.carrentalprototype.pojo.*;

import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.*;

public class ClientHomeFragment extends Fragment {

    TextView pickDate, pickTime, dropDate, dropTime;
    TextView textView;
    private ClientHomeViewModel homeViewModel;
    Spinner pickLocation, dropLocation, vehicleClass, vehicleTYpe, seatSpinner;



    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(ClientHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_client_home, container, false);

        textView = root.findViewById(R.id.continu);
        pickDate = root.findViewById(R.id.pickupdate);
        pickTime = root.findViewById(R.id.pickTime);
        dropDate = root.findViewById(R.id.dropDate);
        dropTime = root.findViewById(R.id.dropTime);
        vehicleClass = root.findViewById(R.id.vehicleClass);
        vehicleTYpe = root.findViewById(R.id.vehicleType);
        pickLocation = root.findViewById(R.id.pickUp);
        dropLocation = root.findViewById(R.id.dropof);
        // textView = root.findViewById(R.id.continu);
        seatSpinner = root.findViewById(R.id.seatsSpinner);


        ArrayList<pojoLocation> spinnerLocation = new ArrayList<>();
        spinnerLocation.add(new pojoLocation(1,"Company`s Location"));
        spinnerLocation.add(new pojoLocation(2,"Montreal Trudeau Airport"));
        spinnerLocation.add(new pojoLocation(3,"Pitsburg Etna"));
        spinnerLocation.add(new pojoLocation(4,"Sher Brook"));
        spinnerLocation.add(new pojoLocation(5,"McGill Downtown"));

        ArrayAdapter<pojoLocation> arrayAdapter = new ArrayAdapter<pojoLocation>(getContext(), android.R.layout.simple_spinner_dropdown_item,spinnerLocation);
        // pickLocation.setAdapter(arrayAdapter);








        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(), "Continue", Toast.LENGTH_SHORT).show();
                //listner.clickbtn();
                //  int m = pickerdate.getDayOfMonth();
                //Toast.makeText(getContext(),m,Toast.LENGTH_SHORT).show();;
/*
              pojoBooking.pickUpLocation = pickLocation.getSelectedItem().toString();
              pojoBooking.dropOffLocation = dropLocation.getSelectedItem().toString();
              pojoBooking.pickUpDate = pickDate.getText().toString();
              pojoBooking.pickUpTime = pickTime.getText().toString();
              pojoBooking.dropOffDate = dropDate.getText().toString();
              pojoBooking.dropOffTime = dropTime.getText().toString();
*/

                Intent i = new Intent(getContext(), clientVehicleList.class);

                String pick = pickLocation.getSelectedItem().toString();
                pick = pick.substring(0,1);

                String drop = dropLocation.getSelectedItem().toString();
                drop = drop.substring(0,1);

                i.putExtra("picklocation",pick);
                i.putExtra("droplocation",  drop);
                i.putExtra("pickUpDate",pickDate.getText().toString());
                i.putExtra("pickUpTime",pickTime.getText().toString());
                i.putExtra("dropOffDate",dropDate.getText().toString());
                i.putExtra("dropOffTime",dropTime.getText().toString());
                i.putExtra("vehicleClass", vehicleClass.getSelectedItem().toString());
                i.putExtra("vehicleType",  vehicleTYpe.getSelectedItem().toString());
                i.putExtra("noOfSeats",  seatSpinner.getSelectedItem().toString());
                startActivity(i);

            }
        });






        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = getInstance();
                int YEAR = calendar.get(Calendar.YEAR);
                int MONTH = calendar.get(Calendar.MONTH);
                int DATE = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        Calendar calendar1 = getInstance();
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, month);
                        calendar1.set(Calendar.DATE, date);
                        String dateText = DateFormat.format("dd MM yyyy", calendar1).toString();

                        pickDate.setText(dateText);
                    }
                }, YEAR, MONTH, DATE);

                datePickerDialog.show();
            }
        });


        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int HOUR = calendar.get(Calendar.HOUR);
                int MINUTE = calendar.get(Calendar.MINUTE);
                boolean is24HourFormat = DateFormat.is24HourFormat(getContext());

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.HOUR, hour);
                        calendar1.set(Calendar.MINUTE, minute);
                        String dateText = DateFormat.format("h:mm a", calendar1).toString();
                        pickTime.setText(dateText);
                    }
                }, HOUR, MINUTE, is24HourFormat);

                timePickerDialog.show();


            }
        });




        dropDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = getInstance();
                int YEAR = calendar.get(Calendar.YEAR);
                int MONTH = calendar.get(Calendar.MONTH);
                int DATE = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        Calendar calendar1 = getInstance();
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, month);
                        calendar1.set(Calendar.DATE, date);
                        String dateText = DateFormat.format("dd MM yyyy", calendar1).toString();

                        dropDate.setText(dateText);
                    }
                }, YEAR, MONTH, DATE);

                datePickerDialog.show();
            }
        });


        dropTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int HOUR = calendar.get(Calendar.HOUR);
                int MINUTE = calendar.get(Calendar.MINUTE);
                boolean is24HourFormat = DateFormat.is24HourFormat(getContext());

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.HOUR, hour);
                        calendar1.set(Calendar.MINUTE, minute);
                        String dateText = DateFormat.format("h:mm a", calendar1).toString();
                        dropTime.setText(dateText);
                    }
                }, HOUR, MINUTE, is24HourFormat);

                timePickerDialog.show();

            }
        });




        return root;
    }

}



