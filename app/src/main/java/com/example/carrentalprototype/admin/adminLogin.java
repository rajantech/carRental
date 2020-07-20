package com.example.carrentalprototype.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.SignUp;
import com.example.carrentalprototype.clientLogIn;
import com.example.carrentalprototype.ruf;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminLogin extends AppCompatActivity {
    EditText username, password;
    Button login;

    RequestQueue requestQueue;
    private StringRequest request;
    String insertUrl = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.email);
        password = findViewById(R.id.pass);

        login = findViewById(R.id.login);


    }
    public void login(View view) {

        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("success")){

                       // Toast.makeText(getApplicationContext(),"SUCCESS "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences = getSharedPreferences(clientLogIn.SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        ruf.adminId=jsonObject.getString("success");

                        editor.putString("ADMIN_MAIL", jsonObject.getString("success"));
                        editor.putString("LOG", "IN");
                        editor.putString("TYPE", "ADMIN");
                         editor.apply();

                        Intent intent = new Intent(getApplicationContext(), adminDashBoard.class);
                        intent.putExtra("name",jsonObject.getString("success"));

                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Error " +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                hashMap.put("email",username.getText().toString());
                hashMap.put("password",password.getText().toString());

                return hashMap;
            }
        };

        requestQueue.add(request);





    }

    public void signUp(View view) {
        Toast.makeText(getApplicationContext(),"signup called",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }

    public void loadClientLogin(View view) {
        Intent intent=new Intent(getApplicationContext(), clientLogIn.class);
        startActivity(intent);
    }
}
