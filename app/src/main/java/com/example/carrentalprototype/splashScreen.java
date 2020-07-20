package com.example.carrentalprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.carrentalprototype.admin.*;



public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ImageView zoom = (ImageView) findViewById(R.id.img);

        final SharedPreferences sharedPreferences = getSharedPreferences(clientLogIn.SHARED_PREFS, MODE_PRIVATE);
        final String mail = sharedPreferences.getString(clientLogIn.MAIL,"");


        final Animation zoomAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.zoom);
        zoom.startAnimation(zoomAnimation);
        zoomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler mHandler = new Handler(getMainLooper());
                Runnable mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        //finish();
                        //Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        //startActivity(i);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(getApplicationContext(),"run", Toast.LENGTH_LONG).show();;


                                if(!mail.equals("") && sharedPreferences.getString("LOG","").equals("IN"))
                                {
                                   // Toast.makeText(getApplicationContext(),"log in",Toast.LENGTH_LONG).show();;

                                    if(sharedPreferences.getString("TYPE","").equals("CLIENT"))
                                    {// Toast.makeText(getApplicationContext(),"type client",Toast.LENGTH_LONG).show();;

                                        ruf.userid = Integer.parseInt(sharedPreferences.getString("uid","0"));

                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                    }
                                    else if(sharedPreferences.getString("TYPE","").equals("ADMIN"))
                                    {
                                        //Toast.makeText(getApplicationContext(),"type admin",Toast.LENGTH_SHORT).show();

                                        ruf.adminId = sharedPreferences.getString("ADMIN_MAIL","0");

                                        Intent i = new Intent(getApplicationContext(), adminDashBoard.class);
                                        startActivity(i);
                                    }

                                }
                                else{
                                    //Toast.makeText(getApplicationContext(),"client Log in",Toast.LENGTH_LONG).show();;

                                    Intent i = new Intent(getApplicationContext(), clientLogIn.class);
                                    startActivity(i);
                                }



                                //  Intent i = new Intent(getApplicationContext(), clientLogIn.class);

                                finish();

                            }
                        },0);


                    }
                };
                mHandler.postDelayed(mRunnable, 2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });








// Toast.makeText(getApplicationContext(),"log : "+sharedPreferences.getString("LOG",""),Toast.LENGTH_LONG).show();;
// Toast.makeText(getApplicationContext(),"TYPE : "+sharedPreferences.getString("TYPE",""),Toast.LENGTH_LONG).show();;
//Toast.makeText(getApplicationContext(),"MAIL : "+sharedPreferences.getString("MAIL",""),Toast.LENGTH_LONG).show();;




    }
}


