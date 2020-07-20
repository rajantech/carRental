package com.example.carrentalprototype.admin.ui.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.example.carrentalprototype.adapter.adapteruserlist;
import com.example.carrentalprototype.admin.*;
import com.example.carrentalprototype.pojo.pojoUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class admin_userList extends Fragment {

    private adminUserListModel dashboardViewModel;



    RecyclerView recyclerView;
    private RequestQueue mQueue;
    private StringRequest request;

    String url = "http://thind.atwebpages.com/index.php";
    adapteruserlist adp;
    List<pojoUser> ar1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(adminUserListModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_userlist, container, false);


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
                        JSONObject jsonObject=    jsona.getJSONObject(i);
                        pojoUser pj=new pojoUser(jsonObject.getString("userName"),jsonObject.getString("userEmail"),jsonObject.getString("city"),jsonObject.getString("userId"),jsonObject.getString("userLastName"));
                            pj.setProfilePic(jsonObject.getString("userProfilePic"));
                        ar1.add(pj);
                    }

                    adp=new adapteruserlist(getContext(),ar1);
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
                hashMap.put("function","userlist");


                return hashMap;
            }
        };

        mQueue.add(request);





        return root;
    }
}