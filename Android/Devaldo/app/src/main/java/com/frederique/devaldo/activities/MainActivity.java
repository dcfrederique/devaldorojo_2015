package com.frederique.devaldo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.frederique.devaldo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }

    public void imageClick(View view) {
        SharedPreferences prefs = getSharedPreferences("devaldo", Context.MODE_PRIVATE);
        String temp = view.getResources().getResourceName(view.getId());
        String user =temp.substring(temp.indexOf('/')+1);
        prefs.edit().putString("chosenPlayer", user).apply();
        Intent i = new Intent(MainActivity.this, HomeScreenActivity.class);
        i.putExtra("user",user);
        startActivity(i);
    }
}
