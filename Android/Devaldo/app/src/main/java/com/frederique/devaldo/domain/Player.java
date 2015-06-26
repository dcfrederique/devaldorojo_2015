package com.frederique.devaldo.domain;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by frederique on 23.06.15.
 */
@ParseClassName("Player")
public class Player extends ParseObject{
    private String position,firstName,lastName;
    private Team team;
    private int number;
    private List<PlayerMatchPresence> playerMatchPresenceList;

    public Player(){

    }
    public List<PlayerMatchPresence> getMatchesOffline()
    {
        ParseQuery<PlayerMatchPresence> query = ParseQuery.getQuery("PlayerMatchPresence");
        query.include("MatchCalendarId");
        query.whereEqualTo("PlayerId", this);
        query.fromLocalDatastore();
        try {
            playerMatchPresenceList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PlayerMatchPresence p : playerMatchPresenceList) {
            p.getMatch();
        }
        return playerMatchPresenceList;
        

    }
    public List<PlayerMatchPresence> getMatchesOnline()
    {
        ParseQuery<PlayerMatchPresence> query = ParseQuery.getQuery("PlayerMatchPresence");
        query.include("MatchCalendarId");
        query.whereEqualTo("PlayerId", this);
        try {
            playerMatchPresenceList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PlayerMatchPresence p : playerMatchPresenceList) {
            p.getMatch();

        }
        try {
            pinAll(playerMatchPresenceList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return playerMatchPresenceList;


    }

    public String getPosition(){
        position = getString("Position");
        return position;
    }
    public String getFirstName(){
        firstName =  getString("FirstName");
        return firstName;
    }
    public Team getTeam(){
        team = (Team)getParseObject("Team");
        return team;
    }
    public String getLastName(){
        lastName = getString("LastName");
        return lastName;
    }
    public int getNumber(){
        number = getNumber("Number").intValue();
        return number;
    }


    public void addMatchPresence(Calendar currentCal) {
        if(!isPresentMatch(currentCal)){
            PlayerMatchPresence pm = new PlayerMatchPresence();
            pm.put("MatchCalendarId",currentCal);
            pm.put("PlayerId",this);
            pm.saveEventually();
        }

    }

    public boolean isPresentMatch(Calendar currentCal) {
        boolean matchFound= false;
        for(PlayerMatchPresence p : getMatchesOffline()) {
            if (p.getMatch().getDate().equals(currentCal.getDate())) {
                //Match found
                matchFound = true;
                break;
            }
        }
        return matchFound;
    }
}
