package com.example.carrentalprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.carrentalprototype.adapter.clientViewVehicleAdapter;
import com.example.carrentalprototype.pojo.pojoVehicle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class clientBookingDetails extends AppCompatActivity {
    TextView vehicleId, rate, title, seats, door, ac, transmision, picklocationTitle, pickstreet, pickpostal, pickcity, pickprovince, dropLocatopTitle, dropStreet, droppostal, dropcity, dropprovince, beforeTax, tax, totalAmount;
    CheckBox usb, insurence;
    String price;
    String ptime,dtime;
    ImageView imgvh;
    int totalAmt;
    int usbcnt=0;


    int babyseatscnt=0;
    int insurancecnt=0;
    int netamount;
    String vid;
    int tx;
    int gross;
    String  imgveh;
    String dpickup,ddropoff;
    String adresspickup,adressdropoff;
    Spinner babySeats;

    Button procede;
    String url = "http://thind.atwebpages.com/index.php";
    private RequestQueue mQueue, mQueue2;
    private StringRequest request, request2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_booking_details);
        babySeats = findViewById(R.id.babySeats);
        usb = findViewById(R.id.u);
        insurence  = findViewById(R.id.insurence);
        rate = findViewById(R.id.rate);
        title = findViewById(R.id.title);
        seats = findViewById(R.id.seats);
        door = findViewById(R.id.door);
        ac = findViewById(R.id.ac);
        transmision = findViewById(R.id.vehicleTransmision);
        picklocationTitle = findViewById(R.id.pickLocationTitle);
        pickstreet = findViewById(R.id.street);
        pickpostal = findViewById(R.id.postal);
        pickcity  = findViewById(R.id.city);
        pickprovince  = findViewById(R.id.province);
        dropLocatopTitle =  findViewById(R.id.dropLocationTitle);
        dropStreet =   findViewById(R.id.dropStreet);
        dropcity =   findViewById(R.id.dropzCity);
        dropprovince  = findViewById(R.id.dropprovince);
        droppostal = findViewById(R.id.dropPostal);
        beforeTax  = findViewById(R.id.beforeTax);
        tax = findViewById(R.id.tax);
        totalAmount = findViewById(R.id.totalAmount);
        procede = findViewById(R.id.procede);
        imgvh=findViewById(R.id.ImageView01);
        vehicleId = findViewById(R.id.vehicleid);

        Intent i = getIntent();
        price=i.getStringExtra("rate");
        totalAmt=Integer.parseInt(price);
        dpickup=i.getStringExtra("datepickup");
        ddropoff=i.getStringExtra("datedropoff");
        imgveh=i.getStringExtra("imagevehicle");
        adresspickup=i.getStringExtra("adresspickup");
        adressdropoff=i.getStringExtra("adressdropoff");
        vid=i.getStringExtra("vehicleid");
        ptime=i.getStringExtra("picktime");
        dtime=i.getStringExtra("droptime");
        adresspickup=  adresspickup.substring(0,1);
        adressdropoff= adressdropoff.substring(0,1);
        vehicleId.setText(" From "+i.getStringExtra("datepickup")+" To "+i.getStringExtra("datedropoff"));
        setTitle("Booking Details");

        mQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonObject = new JSONArray(response);

                    for(int i=0;i<jsonObject.length();i++) {
                        JSONObject o = jsonObject.getJSONObject(i);
                        //pojoVehicle pj=new pojoVehicle(o.getString("vehicleTitle"),o.getString("vehicleSeats"),o.getString("vehicleClas"),o.getString("vehicleId"),o.getString("vehicleDoors"),o.getString("vehicleMillage"),o.getString("vehicleModel"));
                        title.setText(o.getString("vehicleTitle")+" "+o.getString("vehicleModel"));
                        seats.setText(o.getString("vehicleSeats"));
                        rate.setText("Total Journey Cost $"+price);
                        door.setText(o.getString("vehicleDoors"));
                        ac.setText(o.getString("vehicleAc"));
                        transmision.setText(o.getString("vehicleAutoTransmission"));
                        FirebaseStorage firebaseStorage2;
                        StorageReference storageReference2;

                        firebaseStorage2 = FirebaseStorage.getInstance();
                        storageReference2 = firebaseStorage2.getReference();

                        StorageReference imageRef2 = storageReference2.child("Images/"+o.getString("vehicleVehicleImage"));

                        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //Picasso.get().load(uri).into(userpic);
                                Glide.with(getApplicationContext()).load(uri).into(imgvh);


                                // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                            }
                        });

                        //beforeTax.setText("$ "+price);
                        //tx=Integer.parseInt(price);
                        //  tx=(15*tx)/100;
                        //tax.setText("$ "+String.valueOf(tx));


                        //totalAmount.setText("$ "+String.valueOf(totalAmt));


                        insurence.setText(o.getString("bookingInsurance"));
                    }



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
                hashMap.put("dataId",vid);

                return hashMap;
            }

        };
        mQueue.add(request);
        calLocations();

        babySeats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                babyseatscnt=Integer.parseInt(babySeats.getSelectedItem().toString());
                calculateAmount();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        insurence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insurence.isChecked())
                {
                    insurancecnt=1;
                }
                else {
                    insurancecnt=0;
                }
                calculateAmount();
            }
        });

        usb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usb.isChecked())
                {
                    usbcnt=1;
                }
                else {
                    usbcnt=0;
                }
                calculateAmount();
            }
        });

    }




    private void calLocations() {
        mQueue2 = Volley.newRequestQueue(this);

        request2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonObject = new JSONArray(response);

                    JSONObject jsonObject1=  jsonObject.getJSONObject(0);
                    JSONObject jsonObject2=  jsonObject.getJSONObject(1);
                    picklocationTitle.setText(jsonObject1.getString("locationTitle"));
                    pickstreet.setText(jsonObject1.getString("locationBuilding")+" "+jsonObject1.getString("locationStreet"));
                    pickpostal.setText(jsonObject1.getString("locationPostalCode"));
                    pickcity.setText(jsonObject1.getString("locationCity"));
                    pickprovince.setText(jsonObject1.getString("locationProvience"));

                    dropLocatopTitle.setText(jsonObject2.getString("locationTitle"));
                    dropStreet.setText(jsonObject2.getString("locationBuilding")+" "+jsonObject2.getString("locationStreet"));
                    droppostal.setText(jsonObject2.getString("locationPostalCode"));
                    dropcity.setText(jsonObject2.getString("locationCity"));
                    dropprovince.setText(jsonObject2.getString("locationProvience"));


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
                hashMap.put("function","getlocation");

                hashMap.put("dataId",adresspickup);
                hashMap.put("dataIdTwo",adressdropoff);


                return hashMap;
            }

        };
        mQueue2.add(request2);

        calculateAmount();
    }
    public void calculateAmount()
    {
        gross=totalAmt+(usbcnt*5)+(babyseatscnt*20)+(insurancecnt*50);
        tx=(15*gross)/100;
        netamount=gross+tx;
        tax.setText("$ "+String.valueOf(tx));
        beforeTax.setText( "$ "+String.valueOf(gross));
        totalAmount.setText("$ "+String.valueOf(netamount));


    }

    public void loadTerms(View view) {
        Intent i = new Intent(getApplicationContext(),terms_and_conditions.class);
        startActivity(i);
    }


    public void completion(View view) {



        Intent intent=new Intent(getApplicationContext(),BookingCompleted.class);
        intent.putExtra("vid",vid);
        intent.putExtra("pickid",adresspickup);
        intent.putExtra("pdate",dpickup);
        intent.putExtra("ptime",ptime);
        intent.putExtra("dropid",adressdropoff);
        intent.putExtra("ddate",ddropoff);
        intent.putExtra("dtime",dtime);
        intent.putExtra("userid",String.valueOf(ruf.userid));
        intent.putExtra("bseats",String.valueOf(babyseatscnt));
        intent.putExtra("insurence",String.valueOf(insurancecnt));
        intent.putExtra("usb",String.valueOf(String.valueOf(usbcnt)));
        intent.putExtra("total",String.valueOf(gross));
        intent.putExtra("tax",String.valueOf(tx));
        intent.putExtra("net",String.valueOf(netamount));
        startActivity(intent);
    }
}




