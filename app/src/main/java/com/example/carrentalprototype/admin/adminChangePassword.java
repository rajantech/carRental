package com.example.carrentalprototype.admin;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.carrentalprototype.ruf;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminChangePassword extends AppCompatActivity {
    EditText oldpassword,newpassword,newpasswordagain;
    private RequestQueue mQueue;
    StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_password);
        setTitle("Change Password");
        oldpassword=findViewById(R.id.current);
        newpassword=findViewById(R.id.newpass);
        newpasswordagain=findViewById(R.id.newpassagain);
        mQueue= Volley.newRequestQueue(this);

    }
    public void submission(View view) {

        if (newpassword.getText().toString().equals(newpasswordagain.getText().toString()))
        {
            request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String res = jsonObject.getString("value");

                        if (res.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Change Succesfull  ", Toast.LENGTH_LONG).show();
                            oldpassword.setText("");
                            newpassword.setText("");
                            newpasswordagain.setText("");
                        } else if (res.equals("false")) {
                            Toast.makeText(getApplicationContext(), "Change Unsuccesfull.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please Check Your Old Password.", Toast.LENGTH_LONG).show();
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
                    hashMap.put("function", "changepasswordadmin");
                    hashMap.put("userId", String.valueOf(ruf.adminId));
                    hashMap.put("oldPassword", oldpassword.getText().toString());
                    hashMap.put("newPassword", newpassword.getText().toString());

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

