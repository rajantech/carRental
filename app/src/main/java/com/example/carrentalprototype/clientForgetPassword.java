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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class clientForgetPassword extends AppCompatActivity {
TextView email;
    private RequestQueue mQueue;
    private StringRequest request;
    String url = "http://thind.atwebpages.com/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_forget_password);
      //  Toast.makeText(getApplicationContext(),"forget Activity ",Toast.LENGTH_SHORT).show();
        email = findViewById(R.id.pass);
        mQueue= Volley.newRequestQueue(this);



    }

    public void submit(View view) {


        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject=new JSONObject(response);
                    String vlue=jsonObject.getString("value");
                    if (vlue.equals("true"))
                    {
                        //Toast.makeText(getApplicationContext(),"Cancelation successfull",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getApplicationContext(), clientForgetPasswordCode.class);
                        intent.putExtra("email",email.getText().toString());
                        startActivity(intent);
                        //Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        //startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Email Not Found. Try Another Email...",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","findEmail");
                hashMap.put("dataId",email.getText().toString());

                return hashMap;
            }
        };

        mQueue.add(request);





    }
}
