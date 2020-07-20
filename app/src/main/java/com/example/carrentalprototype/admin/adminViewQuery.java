package com.example.carrentalprototype.admin;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminViewQuery extends AppCompatActivity {
Button deleteButton, replyButton;
    TextView fname,lname,sub,desc, queryId, replyEmail;
    String name, queryno, replyOn;
    private RequestQueue mQueue;
    StringRequest request, request2;

    String url = "http://thind.atwebpages.com/index.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_query);

        setTitle("View User Query");

        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastname);
        sub=findViewById(R.id.subject);
        desc=findViewById(R.id.description);
        deleteButton = findViewById(R.id.deleteButton);
        replyButton = findViewById(R.id.replyButton);
        queryId = findViewById(R.id.queryId);
        replyEmail = findViewById(R.id.replyEmail);

        mQueue = Volley.newRequestQueue(this);
        final String data = getIntent().getExtras().getString("queryid");


        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsb=jsonArray.getJSONObject(0);
// String a=jsonObject.getString("bookingTotal");
                    //      Toast.makeText(getApplicationContext(),"inside try :  "+a,Toast.LENGTH_LONG).show();

                    fname.setText(jsb.getString("userName"));
                    lname.setText(jsb.getString("userLastName"));
                    sub.setText(jsb.getString("querySubject"));
                    desc.setText(jsb.getString("queryMessage"));
                    queryId.setText(jsb.getString("queryId"));
                    replyEmail.setText(jsb.getString("queryReplyEmail"));
                    queryno = jsb.getString("queryId");
                    replyOn= jsb.getString("queryReplyEmail");
                    name = jsb.getString("userName")+" "+jsb.getString("userLastName");





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
                hashMap.put("function","querylist");
                hashMap.put("dataId",data);
                return hashMap;
            }

        };
        mQueue.add(request);






        deleteButton.setOnClickListener(new View.OnClickListener() {

            
            @Override
            public void onClick(View v) {
                request2=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                          JSONObject o = new JSONObject(response);

                          if(o.getString("value").equals("true")){
                              Toast.makeText(getApplicationContext(),"Query Deleted..",Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(getApplicationContext(),adminDashBoard.class));
                          }
                          else
                          {
                              Toast.makeText(getApplicationContext(),"Query Not Deleted. Try Again...",Toast.LENGTH_SHORT).show();
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
                        hashMap.put("function","delete");
                        hashMap.put("dataId",data);
                        hashMap.put("type", "query");
                        return hashMap;
                    }

                };
                mQueue.add(request2);


            }
        });



        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={replyOn};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Reply to your query "+queryno);
                intent.putExtra(Intent.EXTRA_TEXT,"Hello "+name);
                intent.putExtra(Intent.EXTRA_CC,"");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });
    }
}

