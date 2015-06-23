package com.frederique.devaldo.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.frederique.devaldo.R;
import com.frederique.devaldo.domain.managers.ParseManager;


public class SplashScreenActivity extends Activity {
    private ParseManager mParseManager;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new RemoteTask().execute();
    }
    private class RemoteTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
                    mParseManager= new ParseManager(context);
                    SharedPreferences prefs = getSharedPreferences("devaldo", Context.MODE_PRIVATE);
                    String s =  prefs.getString("chosenPlayer","not found");
                    //if (s.equals("not found")) {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
               /* }
               else{
                    Intent i = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
                    i.putExtra("user",s);
                    startActivity(i);
                }*/
            return null;
        }
    }

}
