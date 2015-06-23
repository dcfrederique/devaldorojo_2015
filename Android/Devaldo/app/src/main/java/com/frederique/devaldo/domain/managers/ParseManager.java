package com.frederique.devaldo.domain.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.frederique.devaldo.domain.Calendar;
import com.frederique.devaldo.domain.Player;
import com.frederique.devaldo.domain.Team;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Created by frederique on 23.06.15.
 */
public class ParseManager {
    private final Context context;
    private Team team;
    private boolean mHaveNetwork;
    public ParseManager(Context context)
    {
        this.context = context;
        mHaveNetwork = haveNetworkConnection();
        fetchTeam("Devaldo Rojo");
        fetchCalendar();
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    private void fetchCalendar() {
        try{
        ParseQuery<Calendar> query  = ParseQuery.getQuery("Calendar");
        query.include("HomeTeam");
        query.include("AwayTeam");
            if(!mHaveNetwork){
                query.fromLocalDatastore();
            }
        List<Calendar> calendarList = query.find();
        ParseObject.pinAll(calendarList);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void fetchTeam(String s) {
        try {
            ParseQuery<Team> query = ParseQuery.getQuery("Team");
            if(!mHaveNetwork){
                query.fromLocalDatastore();
            }
            team = query.whereEqualTo("Name",s).getFirst();
            List<Player> list = team.getPlayers(mHaveNetwork);
            team.getName();
            team.pin();
            ParseObject.pinAll(list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
