package com.frederique.devaldo.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.frederique.devaldo.R;
import com.frederique.devaldo.domain.Player;
import com.frederique.devaldo.domain.Team;
import com.frederique.devaldo.domain.managers.ParseManager;
import com.frederique.devaldo.fragments.HomeScreenActivityFragment;
import com.parse.ParseException;
import com.parse.ParseQuery;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeScreenActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    private HomeScreenActivityFragment homefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        homefragment = new HomeScreenActivityFragment();
        fragmentTransaction.replace(R.id.frame, homefragment);
        fragmentTransaction.commit();
        SharedPreferences prefs = getSharedPreferences("devaldo", Context.MODE_PRIVATE);
        String temp =  prefs.getString("chosenPlayer", "not found");
        ParseQuery<Team> query = ParseQuery.getQuery(Team.class);
        query.fromLocalDatastore();
        Team team = null;
        try {
            team = query.get("01WLzRUJQl");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Player user = team.searchPlayer(temp);
        CircleImageView imageView = (CircleImageView)navigationView.findViewById(R.id.profile_image);
        int resID = getResources().getIdentifier(temp, "drawable", this.getPackageName());
        imageView.setImageResource(resID);
        TextView textViewUserName = (TextView) navigationView.findViewById(R.id.username);
        textViewUserName.setText(user.getFirstName() + " " + user.getLastName());
        TextView textViewPosition = (TextView) navigationView.findViewById(R.id.position);
        textViewPosition.setText(user.getPosition());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, homefragment);
                        fragmentTransaction.addToBackStack(null);
                        getFragmentManager().executePendingTransactions();
                        fragmentTransaction.commit();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

