package com.example.carrentalprototype.admin.ui.booings;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.adapter.adapterbookinglist;
import com.example.carrentalprototype.admin.adminViewBooking;
import com.example.carrentalprototype.pojo.pojoBookingList;
import com.example.carrentalprototype.ui.home.ClientHomeFragment;

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
public class adminBookinglist extends Fragment {

    RecyclerView recyclerView;
    private RequestQueue mQueue;
    private StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    adapterbookinglist adp;
    List<pojoBookingList> ar1;


    pojoBookingList pj;
    public adminBookinglist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin_bookinglist, container, false);
        recyclerView=root.findViewById(R.id.recy);


        mQueue = Volley.newRequestQueue(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ar1=new ArrayList<>();




        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsona = new JSONArray(response);

                    for(int i=0;i<jsona.length();i++)
                    {

                        JSONObject jsonObject= jsona.getJSONObject(i);
                        pj=new pojoBookingList();
                        pj.setFullname(jsonObject.getString("userName")+" "+jsonObject.getString("userLastName"));
                        pj.setTitle(jsonObject.getString("vehicleTitle"));
                        pj.setPickUpDate(jsonObject.getString("bookingPickUpDate"));
                        pj.setPickUpTime(jsonObject.getString("bookingPickUpTime"));
                        pj.setBookingId(jsonObject.getString("bookingId"));
                        pj.setProfilePic(jsonObject.getString("userProfilePic"));
                        ar1.add(pj);
                    }

                    adp=new adapterbookinglist(getContext(),ar1);
                    recyclerView.setAdapter(adp);
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


                return hashMap;
            }
        };

        mQueue.add(request);


        return root;
    }

}

