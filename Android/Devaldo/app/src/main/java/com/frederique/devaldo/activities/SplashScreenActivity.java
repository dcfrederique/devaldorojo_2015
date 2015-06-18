package com.frederique.devaldo.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.frederique.devaldo.R;


public class SplashScreenActivity extends Activity {
    //SPLASH SCREEN TIMER IN MILLISEC
    private static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("devaldo", Context.MODE_PRIVATE);
                 String s =  prefs.getString("chosenPlayer","not found");
               if (s.equals("not found")) {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
               else{
                    Intent i = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
                    i.putExtra("user",s);
                    startActivity(i);
                }
                finish();
            }
        },TIME_OUT);
    }
}
