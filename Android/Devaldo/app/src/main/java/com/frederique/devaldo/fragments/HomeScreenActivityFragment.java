package com.frederique.devaldo.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frederique.devaldo.R;
import com.frederique.devaldo.domain.Calendar;
import com.frederique.devaldo.domain.Team;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class HomeScreenActivityFragment extends Fragment {
    private List<Calendar> calendarList;

    public HomeScreenActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home_screen, container, false);
        try{
            ParseQuery<Calendar> query  = ParseQuery.getQuery(Calendar.class);
            query.fromLocalDatastore();
            query.include("HomeTeam");
            query.include("AwayTeam");
            calendarList = query.find();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        Calendar c = calendarList.get(0);
        TextView txtMatchInfoTextView = (TextView)v.findViewById(R.id.txtMatchInfo);
        txtMatchInfoTextView.setText(c.getHomeTeam().getName() + " - " + c.getAwayTeam().getName());
        return v;

    }
}
