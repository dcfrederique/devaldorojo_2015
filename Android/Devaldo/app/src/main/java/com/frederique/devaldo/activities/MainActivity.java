package com.frederique.devaldo.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.frederique.devaldo.R;
import com.frederique.devaldo.RecyclerViewDecorations.SimpleDividerItemDecoration;
import com.frederique.devaldo.adapters.UserSelectionAdapter;
import com.frederique.devaldo.domain.UserData;
import com.frederique.devaldo.listeners.RecyclerClickListener;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        mRecyclerView= (RecyclerView)findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
       final  UserData userDataSet[] = {
                new UserData("Giel De Bleser","#33b5e5"),
                new UserData("Jeroen De Winne","#ffbb33"),
                new UserData("Elias De Vos","#ffbb33"),
                new UserData("Frederique De Clercq", "#ff4444"),
                new UserData("Sven De Clercq","#99cc00"),
                new UserData("Jorg De Clercq","#33b5e5"),
                new UserData("Cedric De Pauw","#ffbb33"),
                new UserData("Superfan","#ff4444"),


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
