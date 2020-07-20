package com.example.carrentalprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class clientSetNewPassword extends AppCompatActivity {
TextView pass, repass;
    private RequestQueue mQueue;
    StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_set_new_password);
        pass = findViewById(R.id.pass);
        repass  = findViewById(R.id.repass);

    }

    public void submit(View view) {

        if (pass.getText().toString().equals(repass.getText().toString()))
        {
            request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String res = jsonObject.getString("value");

                        if (res.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Change Succesfull  ", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(), clientLogIn.class);
                            startActivity(intent);

                        }  else {
                            Toast.makeText(getApplicationContext(), "Password Not Changed. Try Again....", Toast.LENGTH_LONG).show();
                        }


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "catch ", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error :  ", Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("function", "changeForgatPassword");
                    hashMap.put("userId", getIntent().getStringExtra("email"));
                    hashMap.put("newPassword", pass.getText().toString());

                    return hashMap;
                }

            };
            mQueue.add(request);

        }

        else {
            Toast.makeText(getApplicationContext(),"Your New Password does not Match",Toast.LENGTH_SHORT).show();
        }





    }
}
