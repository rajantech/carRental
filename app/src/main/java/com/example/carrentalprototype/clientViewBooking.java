package com.example.carrentalprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.carrentalprototype.adapter.adapteruserbookinglist;
import com.example.carrentalprototype.pojo.pojoBookingList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class clientViewBooking extends AppCompatActivity {
    TextView cartitle,ratecar,seatsCar,doorsCar,acCar,transCar,pickUpTitle,streetPickUp,codePostalPickUp,cityPickUp,statePickUp,
            DropOffTitle,streetDropOff,codePostalDropOff,cityDropOff,stateDropOff,babySeatsCar,insuranceCar,usbCar,totalCar,taxCar,netCar;
    TextView countrypickup,countrydropoff, textView;
    LinearLayout cancelSection;
    TextView sts;
    Button btnCancel,bkid;
    ImageView imgcar;
    String bid;
    String stats1;
    private RequestQueue mQueue;
    private StringRequest request;
    String url = "http://thind.atwebpages.com/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view_booking);
        setTitle("My Booking");
        mQueue = Volley.newRequestQueue(this);
        cancelSection = findViewById(R.id.cancelSection);
        textView = findViewById(R.id.textView);
        cartitle=findViewById(R.id.textView2);
        imgcar=findViewById(R.id.ImageView01);
        ratecar =findViewById(R.id.textView3);

        bkid=findViewById(R.id.button);
        seatsCar=  findViewById(R.id.textView6);

        btnCancel=findViewById(R.id.button22);
        doorsCar=  findViewById(R.id.textView7);
        acCar=findViewById(R.id.textView8);
        transCar=findViewById(R.id.textView9);
        pickUpTitle=findViewById(R.id.textView15);
        streetPickUp=findViewById(R.id.pstrt);
        codePostalPickUp=findViewById(R.id.ppostal);
        cityPickUp=findViewById(R.id.pcity);
        statePickUp=findViewById(R.id.pprovince);
        DropOffTitle=findViewById(R.id.textView155);
        streetDropOff=findViewById(R.id.dstrt);
        codePostalDropOff=findViewById(R.id.dpostal);
        cityDropOff=findViewById(R.id.dcity);
        stateDropOff=findViewById(R.id.dprovince);
        babySeatsCar=findViewById(R.id.aSpinner);
        insuranceCar=findViewById(R.id.aSpinner2);
        usbCar=findViewById(R.id.aSpinn);
        totalCar=findViewById(R.id.tex);
        netCar=findViewById(R.id.texe);
        taxCar=findViewById(R.id.texti);
        countrypickup=findViewById(R.id.pcountry);
        countrydropoff=findViewById(R.id.dcountry);
        sts=findViewById(R.id.stts);
        final String data = getIntent().getExtras().getString("bookingid");
        bid=data;
        Intent i = getIntent();
        final String datavehicleclass =    i.getStringExtra("vehicleid");
        //Toast.makeText(getApplicationContext(),datavehicleclass, Toast.LENGTH_SHORT).show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            String vlue=jsonObject.getString("value");
                            if (vlue.equals("true"))
                            {
                                Toast.makeText(getApplicationContext(),"Cancelation successfull",Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Cancelation Unsuccessfull",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("function","cancelbooking");
                        hashMap.put("dataId",data);

                        return hashMap;
                    }
                };

                mQueue.add(request);



            }
        });





        //     Toast.makeText(getApplicationContext()," "+data,Toast.LENGTH_LONG).show();

        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsb=jsonArray.getJSONObject(0);
                    cartitle.setText(jsb.getString("vehicleTitle"));
                    ratecar.setText("CAD "+jsb.getString("vehicleHourRate")+"$/HOUR");
                    bkid.setText("FROM "+jsb.getString("bookingPickUpDate")+" TO "+jsb.getString("bookingDropOffDate"));
                    seatsCar.setText(jsb.getString("vehicleSeats")+ " Seats");
                    doorsCar.setText(jsb.getString("vehicleDoors")+" Doors");
                    acCar.setText(jsb.getString("vehicleAc"));
                    transCar.setText(jsb.getString("vehicleAutoTransmission"));
                    stats1=jsb.getString("bookingStatus");

                    if (stats1.equalsIgnoreCase("canceled") || stats1.equalsIgnoreCase("completed"))
                    {
                        btnCancel.setVisibility(View.GONE);
                    }

                    JSONObject p = jsb.getJSONObject("pickInfo");
                    pickUpTitle.setText(p.getString("locationTitle"));
                    streetPickUp.setText(p.getString("locationBuilding")+" "+p.getString("locationStreet"));
                    codePostalPickUp.setText(p.getString("locationPostalCode"));
                    cityPickUp.setText(p.getString("locationCity"));
                    statePickUp.setText(p.getString("locationProvience"));
                    countrypickup.setText(p.getString("locationCountry"));




                    JSONObject d = jsb.getJSONObject("dropInfo");
                    DropOffTitle.setText(d.getString("locationTitle"));
                    streetDropOff.setText(d.getString("locationBuilding")+" "+d.getString("locationStreet"));
                    codePostalDropOff.setText(d.getString("locationPostalCode"));
                    cityDropOff.setText(d.getString("locationCity"));
                    stateDropOff.setText(d.getString("locationProvience"));
                    countrydropoff.setText(d.getString("locationCountry"));

                    FirebaseStorage firebaseStorage2;
                    StorageReference storageReference2;

                    firebaseStorage2 = FirebaseStorage.getInstance();
                    storageReference2 = firebaseStorage2.getReference();

                    StorageReference imageRef2 = storageReference2.child("Images/"+jsb.getString("vehicleVehicleImage"));

                    imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //  Picasso.get().load(uri).into(holder.);

                            Glide.with(getApplicationContext())
                                    .load(uri)
                                    .into(imgcar);


                            // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                        }
                    });

                    babySeatsCar.setText(jsb.getString("bookingBabySeats"));

                    if(jsb.getString("bookingInsurance").equals("0")) {
                        insuranceCar.setText("Not Included");
                    }
                    else {
                        insuranceCar.setText("Included");
                    }

                   // usbCar.setText(jsb.getString("bookingUsb"));
                    if(jsb.getString("bookingUsb").equals("0")) {
                        usbCar.setText("Not Included");
                    }
                    else {
                        usbCar.setText("Included");
                    }
                    netCar.setText("$"+jsb.getString("bookingNetAmount"));
                    taxCar.setText("$"+jsb.getString("bookingTax"));
                    totalCar.setText("$"+jsb.getString("bookingTotal"));
                    sts.setText(jsb.getString("bookingStatus"));









                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error :  ",Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","bookinglist");
                hashMap.put("dataId",data);
                return hashMap;
            }

        };
        mQueue.add(request);



    }
    public void loadTerms(View view) {
        Intent i = new Intent(getApplicationContext(),terms_and_conditions.class);
        startActivity(i);
    }


}





