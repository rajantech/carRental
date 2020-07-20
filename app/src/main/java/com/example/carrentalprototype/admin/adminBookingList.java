package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.adapter.*;
import com.example.carrentalprototype.pojo.pojoUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.carrentalprototype.adapter.*;
import com.example.carrentalprototype.pojo.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class adminBookingList extends AppCompatActivity {

    RecyclerView recyclerView;
    private RequestQueue mQueue;
    private StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    adapterbookinglist adp;
    List<pojoBookingList> ar1;


    pojoBookingList pj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_list);
        getSupportActionBar().setTitle("Bookings List");

        recyclerView=findViewById(R.id.recy);


        mQueue = Volley.newRequestQueue(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                        ar1.add(pj);
                    }

                    adp=new adapterbookinglist(getApplicationContext(),ar1);
                    recyclerView.setAdapter(adp);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
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













    }



}

