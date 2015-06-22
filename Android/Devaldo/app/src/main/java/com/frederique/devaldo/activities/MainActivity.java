package com.frederique.devaldo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.frederique.devaldo.R;
import com.frederique.devaldo.RecyclerViewDecorations.SimpleDividerItemDecoration;
import com.frederique.devaldo.adapters.UserSelectionAdapter;
import com.frederique.devaldo.domain.UserData;
import com.frederique.devaldo.listeners.RecyclerClickListener;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private Toolbar toolbar;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView= (RecyclerView)findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
       final  UserData userDataSet[] = {
                new UserData("Giel De Bleser"),
                new UserData("Jeroen De Winne"),
                new UserData("Elias De Vos"),
                new UserData("Frederique De Clercq"),
                new UserData("Sven De Clercq"),
                new UserData("Jorg De Clercq"),
                new UserData("Cedric De Pauw"),
                new UserData("Superfan"),


        };
        mAdapter = new UserSelectionAdapter(userDataSet,getApplicationContext());
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        SharedPreferences prefs = getSharedPreferences("devaldo", Context.MODE_PRIVATE);
                        prefs.edit().putString("chosenPlayer", userDataSet[position].getName()).apply();
                        Intent i = new Intent(MainActivity.this, HomeScreenActivity.class);
                        i.putExtra("user",userDataSet[position].getName());
                        startActivity(i);

                    }
                })
        );
    }




}
