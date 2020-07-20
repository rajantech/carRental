package com.example.carrentalprototype.admin;

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
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.ruf;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminChangeEmailCodeConfirmation extends AppCompatActivity {
String newEmail;
TextView message;
ImageView image;

    RequestQueue requestQueue;
    private StringRequest request;
    String insertUrl = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_email_code_confirmation);

        setTitle("Change Email");
        message = findViewById(R.id.mesage);
        image = findViewById(R.id.image);
        newEmail = getIntent().getStringExtra("newEmail");

tocodepage();
    }



    public void tocodepage() {

        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject o = new JSONObject(response);

                    if(o.getString("value").equals("true")){

                        image.setImageResource(R.drawable.success);
                        image.setVisibility(View.VISIBLE);
                        message.setText("Email Changed Successfully.");

                       // Toast.makeText(getApplicationContext(),"Query Deleted..",Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(getApplicationContext(),adminDashBoard.class));
                    }
                    else
                    {
                        image.setImageResource(R.drawable.unsuccess);
                        image.setVisibility(View.VISIBLE);
                        message.setText("Email Not Changed. Try Again...");

                        //Toast.makeText(getApplicationContext(),"Query Not Deleted. Try Again...",Toast.LENGTH_SHORT).show();
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
                hashMap.put("function","adminchangeemail");
                hashMap.put("dataId", String.valueOf(ruf.adminId));
                hashMap.put("newEmail",newEmail);


                return hashMap;
            }
        };

        requestQueue.add(request);






    }

    public void toHome(View view) {
        startActivity(new Intent(getApplicationContext(), adminDashBoard.class));
    }
}
