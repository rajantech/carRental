package com.example.carrentalprototype.ui.myBooking;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.pojo.*;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.example.carrentalprototype.adapter.*;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.*;
import com.example.carrentalprototype.adapter.adapteruserbookinglist;
import com.example.carrentalprototype.pojo.pojoUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class mybooking_client extends Fragment {
    RecyclerView recyclerView;
    adapteruserbookinglist adapterobj;
    private RequestQueue mQueue;
    private StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    List<pojoBookingList> ar1;

    LinearLayout chk;
    private mybookingViewModel mybookingviewmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final LinearLayout loadBookingDetailsClick;









        mybookingviewmodel =
                ViewModelProviders.of(this).get(mybookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mybooking_client, container, false);

        recyclerView=root.findViewById(R.id.recy);

        mQueue = Volley.newRequestQueue(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ar1=new ArrayList<>();

        adapterobj=new adapteruserbookinglist(getContext(),ar1);
        recyclerView.setAdapter(adapterobj);


        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsona = new JSONArray(response);

                    for(int i=0;i<jsona.length();i++)
                    {
                        JSONObject jsonObject=    jsona.getJSONObject(i);
                        pojoBookingList pj=new pojoBookingList();
                        pj.setTitle( jsonObject.getString("vehicleTitle"));
                        JSONObject jsbpick=   jsonObject.getJSONObject("pickInfo");
                        pj.setPickUpLocation(jsbpick.getString("locationTitle"));
                        JSONObject jsbdrop=   jsonObject.getJSONObject("dropInfo");

                        pj.setDropOffLocation(jsbdrop.getString("locationTitle"));
                        pj.setBookingId(jsonObject.getString("bookingId"));
                        pj.setPickUpDate(jsonObject.getString("bookingPickUpDate"));
                        pj.setPickUpTime(jsonObject.getString("bookingPickUpTime"));
                        pj.setBkstatus(jsonObject.getString("bookingStatus"));

                        ar1.add(pj);
                    }

                    adapterobj=new adapteruserbookinglist(getContext(),ar1);
                    recyclerView.setAdapter(adapterobj);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"Catch",Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","bookinglist");
                hashMap.put("userId",String.valueOf(ruf.userid));

                return hashMap;
            }
        };

        mQueue.add(request);











         /*      mybookingviewmodel.getText().observe(this, new Observer<String>() {
           @Override
           public void onChanged(@Nullable String s) {
              // textView.setText(s);
           }
       });

          */
        return root;
    }





}


