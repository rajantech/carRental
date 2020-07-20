package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.clientLogIn;
import com.example.carrentalprototype.ruf;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminChangeEmail extends AppCompatActivity {
EditText currentEmail, newEmail, password;
    RequestQueue requestQueue;
    private StringRequest request;
    String insertUrl = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_email);
        setTitle("Change Email");

        currentEmail = findViewById(R.id.currentEmail);
        newEmail = findViewById(R.id.newEmail);
        password = findViewById(R.id.password);

    }

    public void tocodepage(View view) {

        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("success")){

                        Toast.makeText(getApplicationContext(),"SUCCESS "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(getApplicationContext(),adminChangeEmailCodeConfirmation.class);
                        i.putExtra("newEmail", newEmail.getText().toString());
                        startActivity(i);


                    }else {
                        Toast.makeText(getApplicationContext(), "Error : Curent Email Or Password Not Matched. Try Again..." , Toast.LENGTH_SHORT).show();
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
                hashMap.put("function","adminsignup");
                hashMap.put("email",currentEmail.getText().toString());
                hashMap.put("password",password.getText().toString());

                return hashMap;
            }
        };

        requestQueue.add(request);






    }
}
