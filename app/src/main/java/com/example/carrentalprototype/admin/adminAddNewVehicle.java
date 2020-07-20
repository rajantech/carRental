package com.example.carrentalprototype.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.webkit.MimeTypeMap;
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
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.ruf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminAddNewVehicle extends AppCompatActivity {
    RequestQueue requestQueue;
    Button selectImage;
    ImageView img;

    StorageReference mstorageRef;
    String a;
    String b;
    String userpic;
    public Uri imguri;
    private StorageTask uploadTask;


    EditText title,numberplate,millage,pricehour,model,kilometer;
    Spinner vehicleseats,vehicleclass,vehicletype,vehicledoors,vehicletransmission,vehicleac,vehiclecancellation,vehiclepickup;
    String vseats,vclass,vtype,vdoors,vtransmission,vcancelation,vac,vpickup;
String pickid;
    String insertUrl="http://thind.atwebpages.com/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_vehicle);
        setTitle("Add New Vehicle");



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


        requestQueue= Volley.newRequestQueue(this);
        mstorageRef = FirebaseStorage.getInstance().getReference("Images");








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
        vclass = vehicleclass.getSelectedItem().toString();

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
                            Toast.makeText(getApplicationContext(), "Vehicle Added Succesfully...", Toast.LENGTH_LONG).show();
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
                hashMap.put("function","addvehicle");
                hashMap.put("title",title.getText().toString());
                hashMap.put("pricehourrate",pricehour.getText().toString());
                hashMap.put("cancelation","Included");
                hashMap.put("plate",numberplate.getText().toString());
                hashMap.put("class",vclass);
                hashMap.put("seats",vseats);
                hashMap.put("type",vtype);
                hashMap.put("door",vdoors);
                hashMap.put("ac",vac);
                hashMap.put("transmission",vtransmission);
                hashMap.put("pickid",pickid);
                hashMap.put("milage",millage.getText().toString());
                hashMap.put("model",model.getText().toString());
                hashMap.put("meter",kilometer.getText().toString());
                hashMap.put("image",a+"."+b);

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


}



