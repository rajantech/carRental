package com.example.carrentalprototype.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.carrentalprototype.MainActivity;
import com.example.carrentalprototype.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminViewBooking extends AppCompatActivity {
    Button btn;
    TextView rate, uname, uemail, freecanc, cseats, cdoors, cac, cauto, Pstreet, Ptitle, dtitle,
            Ppostalcode, Pcity, Pprovince, Dstreet, Dpostalcode, Dprovince, Dcity, babyseat,
            insurance, usb, total, tax, netamount;
    Button bcode,bcancel,completedbutton;
    String bkngid;
    String stats;
    private RequestQueue mQueue,mQueue1;
    ImageView imgvehicle, imguser;
    StringRequest request,request1;

    String url = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_booking);
        getSupportActionBar().setTitle("Bookings");

        rate = findViewById(R.id.cost);
        bcode = findViewById(R.id.button);
        uname = findViewById(R.id.userna);
        imguser = findViewById(R.id.ImageView01);
        completedbutton=findViewById(R.id.button23);
        uemail = findViewById(R.id.email);
        cseats = findViewById(R.id.seats);
        cdoors = findViewById(R.id.doors);
        cac = findViewById(R.id.ac);
        cauto = findViewById(R.id.automanual);
        freecanc = findViewById(R.id.textView13);
        Pstreet = findViewById(R.id.ppstreet);
        Ppostalcode = findViewById(R.id.pppostal);
        Pcity = findViewById(R.id.ppcity);
        Pprovince = findViewById(R.id.ppprovince);
        Ptitle = findViewById(R.id.pptitle);
        dtitle = findViewById(R.id.ddtitle);
        Dstreet = findViewById(R.id.ddstreet);
        Dpostalcode = findViewById(R.id.ddpostal);
        Dcity = findViewById(R.id.ddcity);
        bcancel=findViewById(R.id.button22);
        Dprovince = findViewById(R.id.ddprovince);
        babyseat = findViewById(R.id.aSpinner);
        insurance = findViewById(R.id.aSpinner2);

        usb = findViewById(R.id.aSpinner23);
        total = findViewById(R.id.ta);
        tax = findViewById(R.id.tb);
        netamount = findViewById(R.id.tc);
        imgvehicle = findViewById(R.id.ImageView0175);


        mQueue = Volley.newRequestQueue(this);
        final String data = getIntent().getExtras().getString("bkndin");
        bkngid=data;


        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsb = jsonArray.getJSONObject(0);
                    JSONObject jsb2=jsb.getJSONObject("pickInfo");
                    JSONObject jsb3=jsb.getJSONObject("dropInfo");
// String a=jsonObject.getString("bookingTotal");
                    //      Toast.makeText(getApplicationContext(),"inside try :  "+a,Toast.LENGTH_LONG).show();
                    FirebaseStorage firebaseStorage2;
                    StorageReference storageReference2;

                    firebaseStorage2 = FirebaseStorage.getInstance();
                    storageReference2 = firebaseStorage2.getReference();

                    StorageReference imageRef2 = storageReference2.child("Images/" + jsb.getString("vehicleVehicleImage"));
                    imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Picasso.get().load(uri).into(userpic);
                            Glide.with(getApplicationContext()).load(uri).into(imgvehicle);


                            // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                        }
                    });


                    FirebaseStorage firebaseStorage3;
                    StorageReference storageReference3;

                    firebaseStorage3 = FirebaseStorage.getInstance();
                    storageReference3 = firebaseStorage3.getReference();

                    StorageReference imageRef3 = storageReference3.child("Images/" + jsb.getString("userProfilePic"));
                    imageRef3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Picasso.get().load(uri).into(userpic);
                            Glide.with(getApplicationContext()).load(uri).circleCrop().into(imguser);


                            // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Ptitle.setText("Pick Up Location: "+jsb2.getString("locationTitle"));
                    Pstreet.setText(jsb2.getString("locationBuilding")+" - "+jsb2.getString("locationStreet"));
                    Pcity.setText(jsb2.getString("locationStreet"));
                    Ppostalcode.setText(jsb2.getString("locationPostalCode"));
                    Pprovince.setText(jsb2.getString("locationProvience"));

                    dtitle.setText("Drop Off Location: "+jsb3.getString("locationTitle"));
                    Dstreet.setText(jsb3.getString("locationBuilding")+" - "+jsb3.getString("locationStreet"));
                    Dcity.setText(jsb3.getString("locationStreet"));
                    Dpostalcode.setText(jsb3.getString("locationPostalCode"));
                    Dprovince.setText(jsb3.getString("locationProvience"));


                    babyseat.setText(jsb.getString("bookingBabySeats"));
                    insurance.setText(jsb.getString("bookingInsurance"));
                    usb.setText(jsb.getString("bookingUsb"));
                    total.setText(jsb.getString("bookingTotal"));
                    tax.setText(jsb.getString("bookingTax"));
                    netamount.setText(jsb.getString("bookingNetAmount"));
                    stats=jsb.getString("bookingStatus");

                    if (stats.equalsIgnoreCase("canceled") || stats.equalsIgnoreCase("Completed") )
                    {
                        bcancel.setVisibility(View.GONE);
                        completedbutton.setVisibility(View.GONE);

                    }





                    rate.setText("CAD $ "+jsb.getString("vehicleHourRate")+" Per Day");
                    bcode.setText("Booking Id: "+jsb.getString("bookingId"));
                    uname.setText(jsb.getString("userName") + " " + jsb.getString("userLastName"));
                    uemail.setText(jsb.getString("userEmail"));
                    cseats.setText(jsb.getString("vehicleSeats"));
                    cdoors.setText(jsb.getString("vehicleDoors"));
                    cac.setText(jsb.getString("vehicleAc"));
                    cauto.setText(jsb.getString("vehicleAutoTransmission"));
                    freecanc.setText(jsb.getString("FreeCancelation"));










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
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("function", "bookinglist");
                hashMap.put("dataId", data);
                return hashMap;
            }

        };
        mQueue.add(request);

    }

    public void cancela(View view) {


        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String vlue = jsonObject.getString("value");
                    if (vlue.equals("true")) {
                        Toast.makeText(getApplicationContext(), "Cancelation successfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), adminDashBoard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Cancelation Unsuccessfull", Toast.LENGTH_SHORT).show();
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("function", "cancelbooking");
                hashMap.put("dataId", bkngid);

                return hashMap;
            }
        };

        mQueue.add(request);


    }

    public void alreadycompleted(View view) {
        mQueue1 = Volley.newRequestQueue(this);
        request1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String vlue = jsonObject.getString("value");
                    if (vlue.equals("true")) {
                        Toast.makeText(getApplicationContext(), "Completed  Status Submitted", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), adminDashBoard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), " Unsuccessfull", Toast.LENGTH_SHORT).show();
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("function", "completebooking");
                hashMap.put("dataId", bkngid);

                return hashMap;
            }
        };

        mQueue1.add(request1);

    }
}





