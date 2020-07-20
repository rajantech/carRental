package com.example.carrentalprototype.ui.myAccount;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.carrentalprototype.*;
import com.example.carrentalprototype.clientVehicleList;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class myAccountFragment extends Fragment {
    Button changePass, updateInfo, updateLicence, updateImage, changepic;
    TextView lastName,firstName,PhoneNumber,EmailId,AdressFull,Numberlicense;
    ImageView limg , userpic;

    private RequestQueue mQueue;
    StringRequest request;

    String  userPicInfo;

    String url = "http://thind.atwebpages.com/index.php";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);


        changePass = view.findViewById(R.id.changePassword);
        updateInfo = view.findViewById(R.id.updateInfo);
        updateLicence = view.findViewById(R.id.updateLicencce);
        updateImage = view.findViewById(R.id.changepic);
        limg=view.findViewById(R.id.licenseimg);
        userpic = view.findViewById(R.id.ImageView01);
        changepic = view.findViewById(R.id.changepic);

        lastName=view.findViewById(R.id.namel);
        firstName=view.findViewById(R.id.namef);
        PhoneNumber=view.findViewById(R.id.phnnumber);
        EmailId=view.findViewById(R.id.mailadress);
        AdressFull=view.findViewById(R.id.fulladress);
        Numberlicense=view.findViewById(R.id.lnmnumber);



        mQueue= Volley.newRequestQueue(getContext());

        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    //Toast.makeText(getContext(),jsonObject.getString("userEmail"),Toast.LENGTH_SHORT).show();

                    String nm= jsonObject.getString("userName");
                    String lnm=   jsonObject.getString("userLastName");
                    String surmail= jsonObject.getString("userEmail");
                    String phn= jsonObject.getString("number");
                    String adrs0=  jsonObject.getString("apt");
                    String adrs1=   jsonObject.getString("buildingNumber");
                    String adrs2=     jsonObject.getString("street");
                    String adrs3=  jsonObject.getString("city");
                    String adrs4=   jsonObject.getString("postal");
                    String adrs5=  jsonObject.getString("province");
                    String adrs6=      jsonObject.getString("country");
                    String lnmnumber=jsonObject.getString("userLicenceNumber");
                    lastName.setText(jsonObject.getString("userLastName"));

                    userPicInfo = jsonObject.getString("userProfilePic");
                    //Toast.makeText(getContext()," image form volley"+jsonObject.getString("userProfilePic"),Toast.LENGTH_LONG).show();
                    //Toast.makeText(getContext()," strinjgb Value"+userPicInfo,Toast.LENGTH_LONG).show();

                    firstName.setText(nm);

                    PhoneNumber.setText(phn);
                    EmailId.setText(surmail);
                    AdressFull.setText(adrs0+", "+adrs1+", "+adrs2+", "+adrs3+", "+adrs4+", "+adrs5+", "+adrs6);
                    Numberlicense.setText(lnmnumber);

                    /// user Image
                    FirebaseStorage firebaseStorage2;
                    StorageReference storageReference2;

                    firebaseStorage2 = FirebaseStorage.getInstance();
                    storageReference2 = firebaseStorage2.getReference();



//        StorageReference imageRef = storageReference.child("Images/my.png");
                    StorageReference imageRef2 = storageReference2.child("Images/"+userPicInfo);

                    imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                           // Picasso.get().load(uri).into(userpic);
                            Glide.with(getActivity())
                                    .load(uri)
                                    .circleCrop()
                                    .into(userpic);


                           // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                           // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
                        }
                    });






                    // license image
                    FirebaseStorage firebaseStorage;
                    StorageReference storageReference;

                    firebaseStorage = FirebaseStorage.getInstance();
                    storageReference = firebaseStorage.getReference();

//        StorageReference imageRef = storageReference.child("Images/my.png");
                    StorageReference imageRef = storageReference.child("Images/"+jsonObject.getString("userLicenceImage"));

                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Picasso.get().load(uri).into(limg);
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
               // Toast.makeText(getContext(),"Error :  ",Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","userlist");
                hashMap.put("dataId",String.valueOf(ruf.userid));



                return hashMap;
            }

        };
        mQueue.add(request);









            changepic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),"intent Strimg : "+ userPicInfo,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getContext(), uploadImages.class);
                    i.putExtra("userPic",userPicInfo);
                    startActivity(i);
                }
            });



        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), clientChangePassword.class);
                startActivity(i);
            }
        });



        updateLicence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), clientUpdateLicenceInfo.class);
                startActivity(i);
            }
        });


        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), clientupdategeneralinfo.class);
                startActivity(i);
            }
        });





        return view;
    }

}


