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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminViewUser extends AppCompatActivity {
    TextView fnm,lnm,cntc,email,adrs,lnumber;
    ImageView userpic, licenseImage;
    private RequestQueue mQueue;
    StringRequest request, request2;
    Button remove, changeLicense;
    String licenseimage;
String userPicInfo;
   String data;
    String url = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        getSupportActionBar().setTitle("User Information");

        fnm=findViewById(R.id.firstname);
        lnm=findViewById(R.id.lastname);
        cntc=findViewById(R.id.contact);
        email=findViewById(R.id.mail);
        adrs=findViewById(R.id.address);
        lnumber=findViewById(R.id.licensenumber);
        userpic = findViewById(R.id.ImageView01);
        licenseImage  =findViewById(R.id.licenseimg);
        remove = findViewById(R.id.remove);
        changeLicense = findViewById(R.id.changeLicense);


        mQueue = Volley.newRequestQueue(this);
         data = getIntent().getExtras().getString("userid");


        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsb=jsonArray.getJSONObject(0);
// String a=jsonObject.getString("bookingTotal");
                    //      Toast.makeText(getApplicationContext(),"inside try :  "+a,Toast.LENGTH_LONG).show();

                    fnm.setText(jsb.getString("userName"));
                    lnm.setText(jsb.getString("userLastName"));
                    adrs.setText(jsb.getString("apt")+" "+jsb.getString("buildingNumber")+" "+jsb.getString("street")+" "+
                            jsb.getString("postal")+" "+jsb.getString("city")+" "+jsb.getString("province")+" "+jsb.getString("country"));
                    cntc.setText(jsb.getString("number"));
                    email.setText(jsb.getString("userEmail"));
                    lnumber.setText(jsb.getString("userLicenceNumber"));
                    userPicInfo = jsb.getString("userProfilePic");


                    licenseimage= jsb.getString("userLicenceImage");



                    FirebaseStorage firebaseStorage2;
                    StorageReference storageReference2;

                    firebaseStorage2 = FirebaseStorage.getInstance();
                    storageReference2 = firebaseStorage2.getReference();

                    StorageReference imageRef2 = storageReference2.child("Images/"+userPicInfo);

                    imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Picasso.get().load(uri).into(userpic);
                            Glide.with(getApplicationContext()).load(uri).circleCrop().into(userpic);


                            // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                        }
                    });






                    FirebaseStorage firebaseStorage;
                    StorageReference storageReference;

                    firebaseStorage = FirebaseStorage.getInstance();
                    storageReference = firebaseStorage.getReference();

//        StorageReference imageRef = storageReference.child("Images/my.png");
                    StorageReference imageRef = storageReference.child("Images/"+jsb.getString("userLicenceImage"));

                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Picasso.get().load(uri).into(licenseImage);
                            //Picasso.get().load(uri).into(limg);

                            // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
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
                hashMap.put("function","userlist");
                hashMap.put("dataId",data);
                return hashMap;
            }

        };
        mQueue.add(request);








        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request2=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject o = new JSONObject(response);

                            if(o.getString("value").equals("true")){
                                Toast.makeText(getApplicationContext(),"Image Removed Successfully.",Toast.LENGTH_SHORT).show();

                                FirebaseStorage firebaseStorage2;
                                StorageReference storageReference2;

                                firebaseStorage2 = FirebaseStorage.getInstance();
                                storageReference2 = firebaseStorage2.getReference();


                                StorageReference imageRef2 = storageReference2.child("Images/user.png");

                                imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        //Picasso.get().load(uri).into(userpic);
                                        Glide.with(getApplicationContext()).load(uri).circleCrop().into(userpic);


                                        // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                         Toast.makeText(getApplicationContext(),"fail.",Toast.LENGTH_SHORT).show();
                                    }
                                });



                                // startActivity(new Intent(getApplicationContext(), adminViewUser.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Image Not Removed. Try Again.",Toast.LENGTH_SHORT).show();

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
                        hashMap.put("function","removeimage");
                        hashMap.put("dataId",data);
                        return hashMap;
                    }

                };
                mQueue.add(request2);



            }
        });





        changeLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent i = new Intent(getApplicationContext(),adminUpdateLincenseInfo.class);
i.putExtra("dataId",data);
i.putExtra("licenseimage",licenseimage);
startActivity(i);
            }
        });
    }

    public void loadAdminUpdateUserInfo(View view) {
        Intent i = new Intent(getApplicationContext(), adminUpdateUser.class);
        i.putExtra("dataId",data);
        startActivity(i);
    }
}

