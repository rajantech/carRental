package com.example.carrentalprototype;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.example.carrentalprototype.ui.home.ClientHomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookingCompleted extends AppCompatActivity {
    private RequestQueue mQueue;
    StringRequest request;
    Intent i;
    ImageView imgview;
    TextView textView1;

    String url = "http://thind.atwebpages.com/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_completed);
        mQueue= Volley.newRequestQueue(this);
        i=getIntent();
        imgview=findViewById(R.id.imageView11);
        textView1=findViewById(R.id.textv);
        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("value").equals("true"))
                    {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                        imgview.setImageResource(R.drawable.success);
                        imgview.setVisibility(View.VISIBLE);
                        textView1.setText("Booking Succesfull.");


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"UnSuccess",Toast.LENGTH_LONG).show();
                        imgview.setImageResource(R.drawable.unsuccess);
                        imgview.setVisibility(View.VISIBLE);
                        textView1.setText("Booking Unsuccesfull.");
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
                hashMap.put("function","booking");
                hashMap.put("vehicleid",i.getStringExtra("vid"));
                hashMap.put("pickid",i.getStringExtra("pickid"));
                hashMap.put("pdate",i.getStringExtra("pdate"));
                hashMap.put("ptime",i.getStringExtra("ptime"));
                hashMap.put("dropid",i.getStringExtra("dropid"));
                hashMap.put("ddate",i.getStringExtra("ddate"));
                hashMap.put("dtime",i.getStringExtra("dtime"));
                hashMap.put("userid",i.getStringExtra("userid"));
                hashMap.put("bseats",i.getStringExtra("bseats"));
                hashMap.put("insurence",i.getStringExtra("insurence"));
                hashMap.put("usb",i.getStringExtra("usb"));
                hashMap.put("total",i.getStringExtra("total"));
                hashMap.put("tax",i.getStringExtra("tax"));
                hashMap.put("net",i.getStringExtra("net"));
                hashMap.put("status","Active");





                return hashMap;
            }

        };
        mQueue.add(request);






    }

    public void returntohomepage(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


