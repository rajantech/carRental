package com.example.carrentalprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.carrentalprototype.ui.bookingDetail.clientBookingDetails;
import com.example.carrentalprototype.ui.clientVehicleList.clientVehicleList;
import com.example.carrentalprototype.ui.home.ClientHomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import  com.example.carrentalprototype.admin.*;





public class MainActivity extends AppCompatActivity  {
FragmentManager fragmentManager;
FragmentTransaction fragmentTransaction;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







       // Toast.makeText(getApplicationContext(),"main",Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_account, R.id.nav_mybooking,
                R.id.nav_query, R.id.nav_contact, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void loadAdmin(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),adminDashBoard.class);
        startActivity(i);
    }


    public void clientLogOut(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences(clientLogIn.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("LOG","OUT");
        editor.putString(clientLogIn.MAIL,"");
        editor.putString("TYPE","");

        editor.apply();
        Intent intent = new Intent(getApplicationContext(),clientLogIn.class);
        startActivity(intent);
    }
}
