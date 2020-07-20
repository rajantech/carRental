package com.example.carrentalprototype.admin.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.clientLogIn;
import  com.example.carrentalprototype.admin.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {
TextView settings;
TextView bookings, users, vehicle, queries, revenue;

LinearLayout changeEmail, logout, changePass;
    SharedPreferences sharedPreferences;


    private RequestQueue mQueue;
    StringRequest request, request2;

    String url = "http://thind.atwebpages.com/index.php";

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_admin_home, container, false);
        logout = root.findViewById(R.id.logoutAdmin);
        changeEmail = root.findViewById(R.id.chngemail);
        changePass = root.findViewById(R.id.chngpass);

        bookings= root.findViewById(R.id.bookings);
        vehicle= root.findViewById(R.id.vehicles);
        users= root.findViewById(R.id.users);
        queries= root.findViewById(R.id.queries);
        revenue= root.findViewById(R.id.revenue);


         sharedPreferences = this.getActivity().getSharedPreferences(clientLogIn.SHARED_PREFS,MODE_PRIVATE);

       mQueue = Volley.newRequestQueue(getContext());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("LOG","OUT");
                editor.putString(clientLogIn.MAIL,"");
                editor.putString("TYPE","");

                editor.apply();
                Intent intent = new Intent(getContext(),clientLogIn.class);
                startActivity(intent);
            }
        });




        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), adminChangePassword.class);
                startActivity(i);
            }
        });


        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), adminChangeEmail.class);
                startActivity(i);
            }
        });





        request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    JSONObject jsb=jsonArray.getJSONObject(0);
// String a=jsonObject.getString("bookingTotal");
                    //      Toast.makeText(getApplicationContext(),"inside try :  "+a,Toast.LENGTH_LONG).show();

                    bookings.setText(jsb.getString("bookings"));
                    users.setText(jsb.getString("users"));
                    vehicle.setText(jsb.getString("vehicles"));
                    queries.setText(jsb.getString("queries"));
                    revenue.setText("$"+jsb.getString("revenue"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error :  ",Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("function","stats");

                return hashMap;
            }

        };
        mQueue.add(request);







        return root;
    }
}