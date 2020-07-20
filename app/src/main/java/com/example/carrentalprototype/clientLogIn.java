package com.example.carrentalprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.admin.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class clientLogIn extends AppCompatActivity {
    EditText username, password;
    Button login;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String MAIL = "";

    private String EML;

    RequestQueue requestQueue;
    private StringRequest request;
    String insertUrl = "http://thind.atwebpages.com/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_log_in);

        requestQueue = Volley.newRequestQueue(this);
        username = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        loadData();
        updateViews();

        final SharedPreferences sharedPreferences = getSharedPreferences(clientLogIn.SHARED_PREFS, MODE_PRIVATE);
        //  Toast.makeText(getApplicationContext(),"log : "+sharedPreferences.getString("LOG",""),Toast.LENGTH_LONG).show();;
        // Toast.makeText(getApplicationContext(),"TYPE : "+sharedPreferences.getString("TYPE",""),Toast.LENGTH_LONG).show();;
        //Toast.makeText(getApplicationContext(),"MAIL : "+sharedPreferences.getString("MAIL",""),Toast.LENGTH_LONG).show();;

    }

    public void login(View view) {
        request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if(jsonObject.names().get(0).equals("success")){

                       // Toast.makeText(getApplicationContext(),"Welcome "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();

                        //Toast.makeText(getApplicationContext(),jsonObject.getString("userId"),Toast.LENGTH_SHORT).show();


                        ruf.userid =Integer.parseInt(jsonObject.getString("userId"));

                        //String a = jsonObject.getString("userId");
                        // dataPark.userId= Integer.parseInt(a);
                        // dataPark ob = new dataPark(Integer.parseInt(a));


                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(MAIL, username.getText().toString());
                        editor.putString("LOG", "IN");
                        editor.putString("TYPE", "CLIENT");
                        editor.putString("uid", String.valueOf(ruf.userid));

                        editor.apply();


                        // savedata();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        //intent.putExtra("name",jsonObject.getString("success"));
                       // Toast.makeText(getApplicationContext(),"intent",Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                hashMap.put("function","checkLogin");
                hashMap.put("email",username.getText().toString());
                hashMap.put("password",password.getText().toString());

                return hashMap;
            }
        };

        requestQueue.add(request);


    }

    public void signUp(View view) {
        // Toast.makeText(getApplicationContext(),"signup called",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }

    public void loadAdminLogin(View view) {
        Intent intent=new Intent(getApplicationContext(), adminLogin.class);
        startActivity(intent);
    }
    public void savedata()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(MAIL, username.getText().toString());
        editor.putString("LOG", "IN");
        editor.putString("TYPE", "CLIENT");

        editor.apply();

        //   Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        EML = sharedPreferences.getString(MAIL, "");

    }

    public void updateViews() {
        username.setText(EML);

    }

    public void forgetPass(View view) {
        //Toast.makeText(getApplicationContext(),"Forete ",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(), clientForgetPassword.class);
        startActivity(intent);
    }
}

