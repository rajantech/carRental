package com.example.carrentalprototype.ui.contact;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carrentalprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class contact_client extends Fragment {


    public contact_client() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this

        View view =  inflater.inflate(R.layout.fragment_contact_client, container, false);
        return view;
    }

}
