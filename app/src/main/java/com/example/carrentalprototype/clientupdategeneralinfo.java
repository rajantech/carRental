package com.example.carrentalprototype;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class clientupdategeneralinfo extends AppCompatActivity {

    EditText lname,fname,contact,apartment,building,street,postal,city,province,country;
    private RequestQueue mQueue;
    StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientupdategeneralinfo);
        lname=findViewById(R.id.lastnm);
        fname=findViewById(R.id.firstnm);
        contact=findViewById(R.id.contactnumber);
        apartment=findViewById(R.id.apt);
        building=findViewById(R.id.building);
        postal=findViewById(R.id.postalcode);
        street=findViewById(R.id.street);
        city=findViewById(R.id.city);
        province=findViewById(R.id.province);
        country=findViewById(R.id.country);
        mQueue= Volley.newRequestQueue(this);

        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //    Toast.makeText(getApplicationContext(),"try",Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);

                    lname.setText(jsonObject.getString("userLastName"));
                    fname.setText(jsonObject.getString("userName"));
                    contact.setText(jsonObject.getString("number"));
                    apartment.setText(jsonObject.getString("apt"));
                    building.setText(jsonObject.getString("buildingNumber"));
                    postal.setText(jsonObject.getString("postal"));
                    street.setText(jsonObject.getString("street"));
                    city.setText(jsonObject.getString("city"));
                    province.setText(jsonObject.getString("province"));
                    country.setText(jsonObject.getString("country"));





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
                hashMap.put("function","userlist");
                hashMap.put("dataId",String.valueOf(ruf.userid));




                return hashMap;
            }

        };
        mQueue.add(request);






    }

    public void updation(View view) {


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

                    else
                    {
                        Toast.makeText(getApplicationContext(),"Updatio Unsuccesfull ",Toast.LENGTH_LONG).show();
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
                hashMap.put("function","updateuserinfo");
                hashMap.put("dataId",String.valueOf(ruf.userid));
                hashMap.put("lname",lname.getText().toString());
                hashMap.put("fname",fname.getText().toString());
                hashMap.put("mobile",contact.getText().toString());
                hashMap.put("apt",apartment.getText().toString());
                hashMap.put("building",building.getText().toString());
                hashMap.put("street",street.getText().toString());
                hashMap.put("city",city.getText().toString());
                hashMap.put("province",province.getText().toString());
                hashMap.put("country",country.getText().toString());
                hashMap.put("postal",postal.getText().toString());



                return hashMap;
            }

        };
        mQueue.add(request);






    }
}

