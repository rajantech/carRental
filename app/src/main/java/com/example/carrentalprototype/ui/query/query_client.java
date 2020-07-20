package com.example.carrentalprototype.ui.query;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.MainActivity;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.admin.adminViewVehicle;
import com.example.carrentalprototype.clientLogIn;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class query_client extends Fragment {
    EditText subject,fullQuery,replyMail;
    Button submit;
    RequestQueue requestQueue;
    private StringRequest request;
    String insertUrl="http://thind.atwebpages.com/index.php";
    private queryViewModel queryviewmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        queryviewmodel =
                ViewModelProviders.of(this).get(queryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_query_client, container, false);


        subject = root.findViewById(R.id.sub);
        fullQuery=root.findViewById(R.id.fullquery);
        replyMail=root.findViewById(R.id.backmail);
        submit=root.findViewById(R.id.reply);
        requestQueue= Volley.newRequestQueue(getContext());
        String sbj=subject.getText().toString();
        String fq=fullQuery.getText().toString();
        String rm=replyMail.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),subject.getText().toString()+" "+fullQuery.getText().toString()+" "+replyMail.getText().toString(), Toast.LENGTH_SHORT).show();

                request=new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);

                            String aa=jsonObject.getString("value");

                            if(aa.equals("true"))
                            {
                                Toast.makeText(getContext(),"Query Sent.",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                            else {
                                Toast.makeText(getContext(), "Query Not Sent. Try Again...", Toast.LENGTH_LONG).show();
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
                        hashMap.put("function","insertquery");
                        hashMap.put("subject",subject.getText().toString());
                        hashMap.put("message",fullQuery.getText().toString());
                        hashMap.put("userId","26");
                        hashMap.put("replyEmail",replyMail.getText().toString());

                        return hashMap;
                    }
                };
                requestQueue.add(request);


            }
        });

        return root;
    }
}

