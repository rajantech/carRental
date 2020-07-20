package com.example.carrentalprototype.ui.clientVehicleList;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carrentalprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class clientVehicleList extends Fragment {


    public clientVehicleList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_vehicle_list, container, false);


    }




}
