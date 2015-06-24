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

/**
 * Created by frederique on 24.06.15.
 */
public class CalendarAdapter extends FragmentPagerAdapter {
    private Context context;

    public CalendarAdapter(Context context, FragmentManager childFragmentManager) {
        super(childFragmentManager);
        this.context =  context;
    }

    @Override

    public int getCount() {
     return 10;
    }

    @Override
    public Fragment getItem(int position) {
       // Calendar c = calendarlist.get(position);
        return CalendarDetailFragment.newInstance(position);
    }
}
