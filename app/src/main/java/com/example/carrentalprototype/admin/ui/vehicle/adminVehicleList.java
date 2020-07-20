package com.example.carrentalprototype.admin.ui.vehicle;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.carrentalprototype.pojo.pojoVehicle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.carrentalprototype.admin.*;
import com.google.android.material.snackbar.Snackbar;

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
public class adminVehicleList extends Fragment {
    RecyclerView recyclerView;
    private RequestQueue mQueue;
    private StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    adpterVehicleList adp;
    List<pojoVehicle> ar1;

    public adminVehicleList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root = inflater.inflate(R.layout.fragment_admin_vehicle_list, container, false);
        recyclerView=root.findViewById(R.id.recy);
        // requestQueue= Volley.newRequestQueue(this);
        mQueue = Volley.newRequestQueue(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adp);

        ar1=new ArrayList<pojoVehicle>();


        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),adminAddNewVehicle.class));
            }
        });

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsona = new JSONArray(response);

                    for(int i=0;i<jsona.length();i++)
                    {
                        JSONObject jsonObject=    jsona.getJSONObject(i);
                        pojoVehicle pj = new pojoVehicle(jsonObject.getString("vehicleTitle"), jsonObject.getString("vehicleSeats"), jsonObject.getString("vehicleClas"), jsonObject.getString("vehicleId"), jsonObject.getString("vehicleDoors"), jsonObject.getString("vehicleMillage"),jsonObject.getString("vehicleModel"),jsonObject.getString("vehicleHourRate"),jsonObject.getString("vehicleVehicleImage"));
                            pj.setVehicleImage(jsonObject.getString("vehicleVehicleImage"));
                       // pojoUser pj=new pojoUser(jsonObject.getString("userName"),jsonObject.getString("userEmail"),jsonObject.getString("city"),jsonObject.getString("userId"),jsonObject.getString("userLastName"));
                       // pj.setProfilePic(jsonObject.getString("userProfilePic"));

                       // Toast.makeText(getContext(), pj.getVehicleImage(), Toast.LENGTH_SHORT).show();
                        ar1.add(pj);
                    }

                    adp=new adpterVehicleList(getContext(),ar1);
                    recyclerView.setAdapter(adp);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"Catch",Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

              //  Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","vehiclelist");


                return hashMap;
            }
        };

        mQueue.add(request);




        return root;
    }




}
