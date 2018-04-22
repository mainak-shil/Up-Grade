package com.example.root.test;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by root on 17/4/18.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, UserType.class));
                finish();
            }
        }, SPLASH_TIME_OUT);

        if (AppStatus.getInstance(this).isOnline()) {

            Toast.makeText(this, "You are online!!!!", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, "You are not online!!!!", Toast.LENGTH_LONG).show();
            Log.v("Home", "############################You are not online!!!!");
        }

    }
}

