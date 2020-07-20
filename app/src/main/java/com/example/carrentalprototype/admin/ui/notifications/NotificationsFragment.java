package com.example.carrentalprototype.admin.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.adapter.adapterquerylist;
import com.example.carrentalprototype.admin.*;
import com.example.carrentalprototype.pojo.pojoQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationsFragment extends Fragment {

    RecyclerView recyclerView;

    private RequestQueue mQueue;
    private StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    adapterquerylist adp;
    List<pojoQuery> ar1;


    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_admin_notifications, container, false);

        recyclerView=root.findViewById(R.id.recy);


        mQueue = Volley.newRequestQueue(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ar1=new ArrayList<>();



        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsona = new JSONArray(response);

                    for(int i=0;i<jsona.length();i++)
                    {

                        JSONObject jsonObject=jsona.getJSONObject(i);

                        String a = jsonObject.getString("queryMessage");
                      //  a= a.substring(10);
                       // Toast.makeText(getContext(),a,Toast.LENGTH_SHORT).show();
                        pojoQuery pj=new pojoQuery(jsonObject.getString("queryUserId"),jsonObject.getString("queryMessage"),jsonObject.getString("queryId"),jsonObject.getString("userName"),jsonObject.getString("userLastName"));
                       pj.setUserProfilePic(jsonObject.getString("userProfilePic"));
                       pj.setVisit(jsonObject.getString("queryVisit"));
                        ar1.add(pj);
                       // Toast.makeText(getContext(),jsonObject.getString("queryId")+" "+jsonObject.getString("userName")+" "+jsonObject.getString("userLastName"),Toast.LENGTH_SHORT).show();


                    }


                    adp=new adapterquerylist(getContext(),ar1);
                    recyclerView.setAdapter(adp);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"Catch",Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","querylist");


                return hashMap;
            }
        };

        mQueue.add(request);

        return root;
    }
}