package com.frederique.devaldo.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.frederique.devaldo.domain.Calendar;
import com.frederique.devaldo.fragments.CalendarDetailFragment;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by frederique on 24.06.15.
 */
public class CalendarAdapter extends FragmentPagerAdapter {
    private List<Calendar> calendarList = null;
    private Context context;

    public CalendarAdapter(Context context, FragmentManager childFragmentManager) {
        super(childFragmentManager);
        this.context =  context;
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
    }

    @Override

    public int getCount() {
        return calendarList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CalendarDetailFragment.newInstance(position);
    }
    @Override
    public String getPageTitle(int position) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(calendarList.get(position).getDate());

    }
}
