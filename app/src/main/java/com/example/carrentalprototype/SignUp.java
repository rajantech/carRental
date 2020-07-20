package com.example.carrentalprototype;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.carrentalprototype.pojo.pojoUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

String email, password, fname, lname;

    Button register;


    EditText nm,lnm, ema,ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nm = findViewById(R.id.name);
        lnm = findViewById(R.id.lname);
        ema = findViewById(R.id.email);
        ps = findViewById(R.id.pass);
        register = findViewById(R.id.register);



}













    public void backtologin(View view ) {
        Intent intent=new Intent(getApplicationContext(),clientLogIn.class);
        startActivity(intent);
    }


    public void entertosecond(View view) {
        Intent intent=new Intent(this,SIGNUP2.class);
        intent.putExtra("fname",nm.getText().toString());
        intent.putExtra("lname",lnm.getText().toString());
        intent.putExtra("mail",ema.getText().toString());
        intent.putExtra("pass",ps.getText().toString());
        startActivity(intent);
    }
}
