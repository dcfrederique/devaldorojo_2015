package com.frederique.devaldo.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.frederique.devaldo.R;
import com.parse.ParseException;
import com.parse.ParseQuery;
import java.util.Calendar;
import java.util.List;

public class HomeScreenActivityFragment extends Fragment {
    private List<com.frederique.devaldo.domain.Calendar> calendarList;

    public HomeScreenActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home_screen, container, false);
        try{
            ParseQuery<com.frederique.devaldo.domain.Calendar> query  = ParseQuery.getQuery(com.frederique.devaldo.domain.Calendar.class);
            query.fromLocalDatastore();
            query.include("HomeTeam");
            query.include("AwayTeam");
            calendarList = query.find();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        com.frederique.devaldo.domain.Calendar c = calendarList.get(0);
        TextView txtMatchInfoTextView = (TextView)v.findViewById(R.id.txtMatchInfo);
        txtMatchInfoTextView.setText(c.getHomeTeam().getName() + " - " + c.getAwayTeam().getName());
        TextView txtDateTextView = (TextView)v.findViewById(R.id.txtDate);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(c.getDate());
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        txtDateTextView.setText(day + "/" + month);
        return v;

    }
}
