package com.example.carrentalprototype;

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
import com.example.carrentalprototype.adapter.viewvlistAdapter;
import com.example.carrentalprototype.admin.adminViewVehicle;
import com.example.carrentalprototype.pojo.*;
import com.example.carrentalprototype.adapter.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class clientVehicleList extends AppCompatActivity {
    private RequestQueue mQueue;
    private StringRequest request;
    String url = "http://thind.atwebpages.com/index.php";
    RecyclerView recyclerView;
    String dateBeforeString ;
    String dateAfterString ;
    String pickuptime,dropofftime;
    String vehid;
    List<pojoVehicle> ar1;
    String datavehicleclass;
    String datavehicletype,datavehicleseats;
    clientViewVehicleAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_vehicle_list);
        setTitle("Select Car");

        Intent i = getIntent();
        datavehicleclass=   i.getStringExtra("vehicleClass");
        datavehicletype=   i.getStringExtra("vehicleType" );
        datavehicleseats=i.getStringExtra("noOfSeats");
        pickuptime=i.getStringExtra("pickUpTime");
        dropofftime=i.getStringExtra("dropOffTime");

        final String datapickuplocation=    i.getStringExtra("picklocation" );
        pojoBooking.pickUpLocation = datapickuplocation;

        final String datadropofflocation=    i.getStringExtra("droplocation" );
        pojoBooking.dropOffLocation = datadropofflocation;

        String datapickUpDate=    i.getStringExtra("pickUpDate" );
        pojoBooking.pickUpDate  = datapickUpDate;
        dateBeforeString=datapickUpDate;



        String datapickUpTime=    i.getStringExtra("pickUpTime" );
        pojoBooking.pickUpTime = datapickUpTime;


        String datadropOffDate=    i.getStringExtra("dropOffDate" );
        pojoBooking.dropOffDate = datadropOffDate;
        dateAfterString=datadropOffDate;
        String datadropOffTime=    i.getStringExtra("dropOffTime" );
        pojoBooking.dropOffTime=datadropOffTime;




        recyclerView = findViewById(R.id.recy);
        ar1 = new ArrayList<>();

        //  ar1.add(new pojoVehicle("one"));
        //  ar1.add(new pojoVehicle("two"));
        // ar1.add(new pojoVehicle("three"));
        //  ar1.add(new pojoVehicle("four"));





        mQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonObject = new JSONArray(response);

                    for(int i=0;i<jsonObject.length();i++) {
                        JSONObject o = jsonObject.getJSONObject(i);
                        pojoVehicle pj=new pojoVehicle(o.getString("vehicleTitle"),o.getString("vehicleSeats"),o.getString("vehicleClas"),o.getString("vehicleId"),o.getString("vehicleDoors"),o.getString("vehicleMillage"),o.getString("vehicleModel"),o.getString("vehicleHourRate"),o.getString("vehicleVehicleImage"));
                        vehid =o.getString("vehicleId");
                        ar1.add(pj);


                        //Toast.makeText(getApplicationContext(),"i :"+i,Toast.LENGTH_SHORT).show();

                    }


                    adp=new clientViewVehicleAdapter(getApplicationContext(),ar1,dateBeforeString,dateAfterString,datapickuplocation,datadropofflocation,vehid,pickuptime,dropofftime);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adp);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","vehiclelist");

                hashMap.put("vehicleType",datavehicletype);
                hashMap.put("vehicleClass",datavehicleclass);
                hashMap.put("seatsOfCar",datavehicleclass);


                //hashMap.put("pickUpLocation","1");


                return hashMap;
            }

        };

        mQueue.add(request);

    }


}




