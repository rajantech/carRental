package com.example.carrentalprototype.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminUpdateVehicle extends AppCompatActivity {
    RequestQueue requestQueue;
    StringRequest request2;
    Button selectImage;
    ImageView img;

    StorageReference mstorageRef;
    String a;
    String b;
    String userpic;
    public Uri imguri;
    private StorageTask uploadTask;

    String data;
    EditText title,numberplate,millage,pricehour,model,kilometer;
    Spinner vehicleseats,vehicleclass,vehicletype,vehicledoors,vehicletransmission,vehicleac,vehiclecancellation,vehiclepickup;
    String vseats,vclass,vtype,vdoors,vtransmission,vcancelation,vac,vpickup;
    String pickid;
    String insertUrl="http://thind.atwebpages.com/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_vehicle);
        setTitle("Update Vehicle");



        title=findViewById(R.id.title);
        numberplate=findViewById(R.id.numberPlate);
        millage=findViewById(R.id.Millage);
        pricehour=findViewById(R.id.PriceHour);
        model=findViewById(R.id.Model);
        kilometer=findViewById(R.id.kilometer);
        selectImage = findViewById(R.id.selectImage);
        img = findViewById(R.id.licenseimg);

        vehicleseats = (Spinner) findViewById(R.id.vehicleSeats);
        vseats = vehicleseats.getSelectedItem().toString();
        vehicleclass = (Spinner) findViewById(R.id.vehicleClass);
        vclass = vehicleclass.getSelectedItem().toString();
        vehicletype = (Spinner) findViewById(R.id.vehicleSeatsee);
        vtype = vehicletype.getSelectedItem().toString();

        vehicledoors = (Spinner) findViewById(R.id.vehicleDoors);
        vdoors = vehicledoors.getSelectedItem().toString();

        vehicletransmission = (Spinner) findViewById(R.id.vehicleTransmision);
        vtransmission = vehicletransmission.getSelectedItem().toString();

        vehicleac = (Spinner) findViewById(R.id.vehicleac);
        vac = vehicleac.getSelectedItem().toString();


        vehiclepickup = (Spinner) findViewById(R.id.vehiclePickUp);
        vpickup = vehiclepickup.getSelectedItem().toString();

        pickid = vpickup.substring(0,1);

         data = getIntent().getExtras().getString("vehicleid");
        requestQueue= Volley.newRequestQueue(this);
        mstorageRef = FirebaseStorage.getInstance().getReference("Images");

        initiateData();






        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });

    }



    public void addVeicle(View view) {


        vdoors = vehicledoors.getSelectedItem().toString();
        vtransmission = vehicletransmission.getSelectedItem().toString();
        vac = vehicleac.getSelectedItem().toString();
        vpickup = vehiclepickup.getSelectedItem().toString();
        pickid = vpickup.substring(0,1);

        //Toast.makeText(getApplicationContext(),"Add Called",Toast.LENGTH_SHORT).show();

        if(uploadTask != null && uploadTask.isInProgress()) {
            Toast.makeText(getApplicationContext(), "Upload in progress", Toast.LENGTH_LONG).show();

        } else {
            Fileuploader();
        }

        StringRequest request=new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),"try",Toast.LENGTH_SHORT).show();

                    JSONObject jsonObject=new JSONObject(response);


                    if (jsonObject.getString("value").equals("true")) {
                        Toast.makeText(getApplicationContext(), "Vehicle Updated Succesfully...", Toast.LENGTH_LONG).show();
                        finish();
                    }  else {
                        Toast.makeText(getApplicationContext(), " Unsuccesfull. Try Again...", Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","updatevehicle");
                hashMap.put("dataId",data);
                hashMap.put("title",title.getText().toString());
                hashMap.put("rate",pricehour.getText().toString());
                hashMap.put("cancelation","Included");
                hashMap.put("numberPlate",numberplate.getText().toString());
                hashMap.put("clas",vclass);
                hashMap.put("seats",vseats);
                hashMap.put("type",vtype);
                hashMap.put("doors",vdoors);
                hashMap.put("ac",vac);
                hashMap.put("transmission",vtransmission);
                hashMap.put("pickid",pickid);
                hashMap.put("milage",millage.getText().toString());
                hashMap.put("model",model.getText().toString());
                hashMap.put("metereading",kilometer.getText().toString());
                hashMap.put("vehicleImage",a+"."+b);

                return hashMap;
            }
        };
        requestQueue.add(request);

    }








    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }



    private void Fileuploader() {

        //StorageReference ref = mstorageRef.child(System.currentTimeMillis()
        b=getExtension(imguri);
        a= "vehicle"+System.currentTimeMillis();
        StorageReference ref = mstorageRef.child(a
                + "." + getExtension(imguri) );
        uploadTask = ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getUploadSessionUri();  //getDownloadUrl();
                        Toast.makeText(getApplicationContext(),
                                "Image uploaded", Toast.LENGTH_LONG).show();
                        //toserveraswell();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK && data !=null && data.getData()!= null)
        {
            imguri = data.getData();
            img.setImageURI(imguri);
            img.setVisibility(View.VISIBLE);

        }
    }







    public void downloadImage(){
        FirebaseStorage firebaseStorage;
        StorageReference storageReference;

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

//        StorageReference imageRef = storageReference.child("Images/my.png");
        StorageReference imageRef = storageReference.child("Images/"+userpic);

        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(img);

                //Toast.makeText(getApplicationContext(),"Success.",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(),"fail.",Toast.LENGTH_SHORT).show();
            }
        });
    }







    private void initiateData() {
        request2=new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsb=jsonArray.getJSONObject(0);
// String a=jsonObject.getString("bookingTotal");
                    //      Toast.makeText(getApplicationContext(),"inside try :  "+a,Toast.LENGTH_LONG).show();

                    title.setText(jsb.getString("vehicleTitle"));
                    millage.setText(jsb.getString("vehicleMillage"));
                    model.setText(jsb.getString("vehicleModel"));

                   // vseats.setText(jsb.getString("vehicleSeats"));
                    vehicleseats.setSelection(((ArrayAdapter)vehicleseats.getAdapter()).getPosition(jsb.getString("vehicleSeats")));

                   // acc.setText(jsb.getString("vehicleAc"));
                    vehicleac.setSelection(((ArrayAdapter)vehicleac.getAdapter()).getPosition(jsb.getString("vehicleAc")));


                    numberplate.setText(jsb.getString("vehicleNumberPlate"));

                    kilometer.setText(jsb.getString("vehicleMeterReading"));
                    pricehour.setText(jsb.getString("vehicleHourRate"));

                  //  vdoors.setText(jsb.getString("vehicleDoors")+" Doors");
                    vehicledoors.setSelection(((ArrayAdapter)vehicledoors.getAdapter()).getPosition(jsb.getString("vehicleDoors")));



                   // atmnl.setText(jsb.getString("vehicleAutoTransmission"));
                    vehicletransmission.setSelection(((ArrayAdapter)vehicletransmission.getAdapter()).getPosition(jsb.getString("vehicleAutoTransmission")));

                    //cls.setText(jsb.getString("vehicleClas"));
                    vehicleclass.setSelection(((ArrayAdapter)vehicleclass.getAdapter()).getPosition(jsb.getString("vehicleClas")));

                   //typ.setText(jsb.getString("vehicleType"));
                    vehicletype.setSelection(((ArrayAdapter)vehicletype.getAdapter()).getPosition(jsb.getString("vehicleType")));


                    JSONObject pick=jsb.getJSONObject("pickInfo");
                   // pickup.setText("Pick Up At : "+pick.getString("locationTitle"));

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
                            Glide.with(getApplicationContext()).load(uri).into(img);
                            img.setVisibility(View.VISIBLE);


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
        requestQueue.add(request2);


    }

}



