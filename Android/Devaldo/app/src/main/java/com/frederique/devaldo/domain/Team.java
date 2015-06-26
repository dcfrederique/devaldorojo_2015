package com.frederique.devaldo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Created by frederique on 23.06.15.
 */
@ParseClassName("Team")
public class Team extends ParseObject {
    private String name;
    private List<Player> playerList;

    public Team() {

    }


    public List<Player> getPlayers(boolean mHaveNetwork) {
        ParseQuery<Player> query = ParseQuery.getQuery("Player");
        if(!mHaveNetwork){
            query.fromLocalDatastore();
        }
        query.include("Team");
        query.whereEqualTo("Team", this);
        try {
            playerList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Player p : playerList) {
            p.getFirstName();
            p.getTeam();
            p.getMatchesOnline();
            p.getLastName();
            p.getNumber();
            p.getPosition();
        }

        return playerList;
    }

    public String getName()
    {
        name = getString("Name");
        return name;
    }

    public Player searchPlayer(String userName) {
        Player searchPlayer = null;
        for (Player p : playerList) {
            if (p.getFirstName().equalsIgnoreCase(userName)) {
                searchPlayer = p;
            }
        }
        return searchPlayer;
    }
}
