package com.sis21a.e_commerce.Splash;

import androidx.appcompat.app.AppCompatActivity;

import com.sis21a.e_commerce.MenuFloating;
import com.sis21a.e_commerce.R;
import com.sis21a.e_commerce.UI.sesion.login;
import com.sis21a.e_commerce.MenuFloating;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(com.sis21a.e_commerce.Splash.splash.this, login.class);
                startActivity(i);
                finish();
            }
        }, 2200);
    }



}