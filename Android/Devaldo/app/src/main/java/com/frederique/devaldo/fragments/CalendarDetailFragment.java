package com.frederique.devaldo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.frederique.devaldo.R;
import com.frederique.devaldo.domain.Calendar;
import com.frederique.devaldo.domain.Player;
import com.frederique.devaldo.domain.PlayerMatchPresence;
import com.frederique.devaldo.domain.Team;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by frederique on 24.06.15.
 */
public class CalendarDetailFragment  extends Fragment {
    private static final String KEY_POSITION="position";
    private static Calendar currentCal;
    private Player user;

    public static CalendarDetailFragment newInstance(int position)
    {
        CalendarDetailFragment frag=new CalendarDetailFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.calendar_detail_fragment, container, false);
        int position=getArguments().getInt(KEY_POSITION, -1);
        currentCal = fetchCalendar(position);
        TextView txtView = (TextView)result.findViewById(R.id.txtCalendarDetailView);
        txtView.setText(currentCal.getHomeTeam().getName() + " - " + currentCal.getAwayTeam().getName());

        ParseQuery<Team> query = ParseQuery.getQuery(Team.class);
        query.fromLocalDatastore();
        Team team = null;
        try {
            team = query.get("01WLzRUJQl");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs = getActivity().getSharedPreferences("devaldo", Context.MODE_PRIVATE);
        String temp =  prefs.getString("chosenPlayer", "not found");
         user = team.searchPlayer(temp);

        SwitchCompat presenceSwitch =(SwitchCompat) result.findViewById(R.id.presence_switch);
        presenceSwitch.setChecked(user.isPresentMatch(currentCal));
        presenceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    user.addMatchPresence(currentCal);
                }
                //else delete match
            }
        });
        return result ;
    }

    private Calendar fetchCalendar(int position) {
        List<Calendar> calendarList = null;
        try {
            ParseQuery<Calendar> query = ParseQuery.getQuery(com.frederique.devaldo.domain.Calendar.class);
            query.fromLocalDatastore();
            query.include("HomeTeam");
            query.include("AwayTeam");
            query.orderByAscending("Date");
            calendarList = query.find();

        } catch (ParseException e) {
            e.printStackTrace();
        }
       return calendarList.get(position);
    }
}
