package com.frederique.devaldo.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frederique.devaldo.R;

public class HomeScreenActivityFragment extends Fragment {

    public HomeScreenActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home_screen, container, false);
       // TextView txtView = (TextView) v.findViewById(R.id.txtViewHomeScreen);
       // txtView.setText(getActivity(). getIntent().getExtras().getString("user"));
        return v;
    }
}
