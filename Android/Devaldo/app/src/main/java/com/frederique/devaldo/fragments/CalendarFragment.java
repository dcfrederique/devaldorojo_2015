package com.frederique.devaldo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frederique.devaldo.R;
import com.frederique.devaldo.adapters.CalendarAdapter;

import java.util.Date;

/**
 * Created by frederique on 24.06.15.
 */
public class CalendarFragment extends Fragment {

    public CalendarFragment(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar_screen, container, false);
       int myInt = getArguments().getInt("pos", 0);
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());
        pager.setCurrentItem(myInt);
        return v;

    }

    private PagerAdapter buildAdapter() {
        return new CalendarAdapter(getActivity(), getChildFragmentManager());
    }
}
