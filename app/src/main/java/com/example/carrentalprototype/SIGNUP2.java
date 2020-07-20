package com.example.carrentalprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SIGNUP2 extends AppCompatActivity {
EditText number, buildingnumbr, stret, city, provience, country, postal, apt;
RequestQueue requestQueue;
    String fname,lname,mail,pass;
    private StringRequest request;
    String insertUrl = "http://thind.atwebpages.com/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        number = findViewById(R.id.number);
        requestQueue= Volley.newRequestQueue(this);
        apt = findViewById(R.id.apt);
        buildingnumbr = findViewById(R.id.buildingnumber);
        stret = findViewById(R.id.street);
        city = findViewById(R.id.city);
        provience = findViewById(R.id.province);
        country = findViewById(R.id.country);
        postal = findViewById(R.id.postal);

       // db = new helper(this);

fname=getIntent().getStringExtra("fname");
        lname=getIntent().getStringExtra("lname");
        mail=getIntent().getStringExtra("mail");
pass=getIntent().getStringExtra("pass");
    }



    public void RegisterData(View view) {




        request=new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    if(jsonObject.names().get(0).equals("error"))
                    {
                        Toast.makeText(getApplicationContext(), "ERROR : " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if (jsonObject.getString("value").equals("true")) {
                            Toast.makeText(getApplicationContext(), "Registration Succesfull. LogIn Now...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),clientLogIn.class);
                            startActivity(intent);
                        }  else {
                            Toast.makeText(getApplicationContext(), "Registration Unsuccesfull ", Toast.LENGTH_LONG).show();

                        }
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
                hashMap.put("function","signup");
                hashMap.put("email",mail);
                hashMap.put("password",pass);
                hashMap.put("name",fname);
                hashMap.put("lastName",lname);
                hashMap.put("number",number.getText().toString());
                hashMap.put("apt",apt.getText().toString());
                hashMap.put("buildingNumber",buildingnumbr.getText().toString());
                hashMap.put("street",stret.getText().toString());
                hashMap.put("city",city.getText().toString());
                hashMap.put("province",provience.getText().toString());
                hashMap.put("country",country.getText().toString());
                hashMap.put("postal",postal.getText().toString());

                return hashMap;
            }
        };
        requestQueue.add(request);

    }
}
