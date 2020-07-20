package com.example.carrentalprototype.ui.logout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carrentalprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class logout_client extends Fragment {


    public logout_client() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout_client, container, false);
    }

}
