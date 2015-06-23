package com.frederique.devaldo;

import android.app.Application;

import com.frederique.devaldo.domain.Player;
import com.frederique.devaldo.domain.Team;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Team.class);
        ParseObject.registerSubclass(Player.class);
        Parse.initialize(this,"xRnSH91gots8F33V1VHy9mW9S0spaNRPVHUoPv14","AvdAmhkP5awYDa0YACnv7VpLWmEShBbwXopYJpHw");
    }
}
