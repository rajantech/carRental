package com.example.carrentalprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.adapter.adapterquerylist;
import com.example.carrentalprototype.pojo.pojoQuery;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ruf extends AppCompatActivity {
    String a;
    public static int userid;
    public  static String adminId;

    TextView  name;

    RequestQueue requestQueue;
    private StringRequest request;
    String url = "http://thind.atwebpages.com/index.php";
    String insertUrl = "http://thind.atwebpages.com/sample.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruf);
        name = findViewById(R.id.text);
        //ppu = findViewById(R.id.ppu);
       // batter = findViewById(R.id.batters);
        //  topping = findViewById(R.id.topping);

String a = "barinder";
a=a.substring(2,5);
Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();


        //id2 = findViewById(R.id.id2);
       // type2 = findViewById(R.id.type2);
       // name2 = findViewById(R.id.name2);
        requestQueue = Volley.newRequestQueue(this);
       // ppu2 = findViewById(R.id.ppu2);
       // batter2 = findViewById(R.id.batters2);

    }



    public void submit(View view) {
       // name.setText("called");





        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsona = new JSONArray(response);

                    for(int i=0;i<5;i++)
                    {

                        JSONObject jsonObject=jsona.getJSONObject(i);

                       name.setText(jsonObject.getString("queryMessage"));
                          a= name.getText().toString().substring(10,40);
                       // Toast.makeText(getContext(),a,Toast.LENGTH_SHORT).show();
                       // pojoQuery pj=new pojoQuery(jsonObject.getString("queryUserId"),jsonObject.getString("queryMessage"),jsonObject.getString("queryId"),jsonObject.getString("userName"),jsonObject.getString("userLastName"));
                       // ar1.add(pj);
                        // Toast.makeText(getApplicationContext(),jsonObject.getString("queryId")+" "+jsonObject.getString("userName")+" "+a,Toast.LENGTH_SHORT).show();


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","querylist");


                return hashMap;
            }
        };

        requestQueue.add(request);
    }
}