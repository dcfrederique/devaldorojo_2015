package com.frederique.devaldo.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Created by frederique on 23.06.15.
 */
@ParseClassName("Team")
public class Team extends ParseObject implements Parcelable {
    private String name;
    private List<Player> playerList;
    public Team() {

    }

    public Team(Parcel in)
    {
        this.name = in.readString();
        this.playerList = in.readArrayList(Player.class.getClassLoader());
    }


    public List<Player> getPlayers() {
        ParseQuery<Player> query = ParseQuery.getQuery("Player");
        query.include("Team");
        query.whereEqualTo("Team", this);
        try {
            playerList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Player p : playerList) {
            p.getFirstName();
            //p.getTeam();
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
    @Override
    public int describeContents()
    {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeList(playerList);
    }
    public static final Parcelable.Creator<Team> CREATOR
            = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
