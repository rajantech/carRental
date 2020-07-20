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

public class adminViewVehicle extends AppCompatActivity {
    Button btn, pickup, deleteButton;
ImageView vehicleImage;

    private RequestQueue mQueue;
    StringRequest request, request2;

    String url = "http://thind.atwebpages.com/index.php";



    TextView ttl,sts,acc,nmbrplt,rdng,cst,drs,atmnl,cls,typ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_vehicle);
        getSupportActionBar().setTitle("Vehicle Detalis");
        ttl=findViewById(R.id.title);
        sts=findViewById(R.id.seats);
        acc=findViewById(R.id.ac);
        nmbrplt=findViewById(R.id.numberplate);
        rdng=findViewById(R.id.reading);
        cst=findViewById(R.id.cost);
        drs=findViewById(R.id.doors);
        atmnl=findViewById(R.id.automanual);
        cls=findViewById(R.id.cls);
        typ=findViewById(R.id.type);
        pickup = findViewById(R.id.button);
        deleteButton = findViewById(R.id.delete);
        vehicleImage = findViewById(R.id.ImageView01);

        mQueue=Volley.newRequestQueue(this);
        final String data = getIntent().getExtras().getString("vehicleid");


        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsb=jsonArray.getJSONObject(0);
// String a=jsonObject.getString("bookingTotal");
                    //      Toast.makeText(getApplicationContext(),"inside try :  "+a,Toast.LENGTH_LONG).show();

                    ttl.setText(jsb.getString("vehicleTitle"));
                    sts.setText(jsb.getString("vehicleSeats") + " Seats");
                    acc.setText(jsb.getString("vehicleAc"));
                    nmbrplt.setText(jsb.getString("vehicleNumberPlate"));
                    rdng.setText(jsb.getString("vehicleMeterReading"));
                    cst.setText("$"+jsb.getString("vehicleHourRate")+" / Per Day");
                    drs.setText(jsb.getString("vehicleDoors")+" Doors");
                    atmnl.setText(jsb.getString("vehicleAutoTransmission"));
                    cls.setText(jsb.getString("vehicleClas"));
                    typ.setText(jsb.getString("vehicleType"));

                    JSONObject pick=jsb.getJSONObject("pickInfo");
                    pickup.setText("Pick Up At : "+pick.getString("locationTitle"));

                   // Toast.makeText(getApplicationContext(),pick.getString("locationTitle"),Toast.LENGTH_SHORT).show();




                    FirebaseStorage firebaseStorage2;
                    StorageReference storageReference2;

                    firebaseStorage2 = FirebaseStorage.getInstance();
                    storageReference2 = firebaseStorage2.getReference();

                    StorageReference imageRef2 = storageReference2.child("Images/"+jsb.getString("vehicleVehicleImage"));

                    imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Picasso.get().load(uri).into(userpic);
                            Glide.with(getApplicationContext()).load(uri).into(vehicleImage);
                            vehicleImage.setVisibility(View.VISIBLE);


                            // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                        }
                    });







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
                hashMap.put("function","vehiclelist");
                hashMap.put("dataId",data);
                return hashMap;
            }

        };
        mQueue.add(request);




        deleteButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                request2=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject o = new JSONObject(response);

                            if(o.getString("value").equals("true")){
                                Toast.makeText(getApplicationContext(),"Vehicle Deleted..",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),adminDashBoard.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Vehicle Not Deleted. Try Again...",Toast.LENGTH_SHORT).show();
                            }


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
                        hashMap.put("function","delete");
                        hashMap.put("dataId",data);
                        hashMap.put("type", "vehicle");
                        return hashMap;
                    }

                };
                mQueue.add(request2);


            }
        });




        btn= findViewById(R.id.viewVehicle);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), adminUpdateVehicle.class);
                i.putExtra("vehicleid",data);
                startActivity(i);
            }
        });
    }
}

