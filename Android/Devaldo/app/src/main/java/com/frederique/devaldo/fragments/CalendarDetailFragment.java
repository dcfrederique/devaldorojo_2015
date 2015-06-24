package com.frederique.devaldo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frederique.devaldo.R;

/**
 * Created by frederique on 24.06.15.
 */
public class CalendarDetailFragment  extends Fragment {
    private static final String KEY_POSITION="position";
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
        TextView txtView = (TextView)result.findViewById(R.id.txtCalendarDetailView);
        txtView.setText("test" + position);
        return result ;
    }
}
