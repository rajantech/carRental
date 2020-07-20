package com.example.carrentalprototype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class uploadImages extends AppCompatActivity {
    Button ch, up, down;
    ImageView img, downimage;

    StorageReference mstorageRef;
    String a;
    String b;
    String userpic;
    public Uri imguri;
    private StorageTask uploadTask;

    private RequestQueue mQueue;
    StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);
        setTitle("Change Profile Image");

        mstorageRef = FirebaseStorage.getInstance().getReference("Images");
        mQueue= Volley.newRequestQueue(this);
        ch= (Button) findViewById(R.id.button);
        up = (Button) findViewById(R.id.button2);

        img = (ImageView) findViewById(R.id.imageView);


userpic = getIntent().getExtras().getString("userPic");

            downloadImage();


        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FileChooser();

                if(uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(getApplicationContext(), "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                }

            }
        });
    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }



    private void Fileuploader() {

        //StorageReference ref = mstorageRef.child(System.currentTimeMillis()
        b=getExtension(imguri);
        a=ruf.userid+"_profilePic";//System.currentTimeMillis();
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
                        toserveraswell();

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

        }
    }




    public void toserveraswell()
    {
        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String res =  jsonObject.getString("value");

                             if (res.equals("true"))
                             {
                                     Toast.makeText(getApplicationContext(),"Updation Succesfull  ",Toast.LENGTH_LONG).show();
                                }


                            else {
                                         Toast.makeText(getApplicationContext(),"Unsuccesfull ",Toast.LENGTH_LONG).show();
                                 }


                } catch (JSONException e) {
                   // Toast.makeText(getApplicationContext(),"catch ",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"Error :  ",Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","updateuserimage");
                hashMap.put("dataId",String.valueOf(ruf.userid));
                hashMap.put("imageInfo",a+"."+b);




                return hashMap;
            }

        };
        mQueue.add(request);





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

