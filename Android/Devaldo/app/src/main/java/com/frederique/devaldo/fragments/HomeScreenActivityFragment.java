package com.frederique.devaldo.fragments;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frederique.devaldo.R;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

public class HomeScreenActivityFragment extends Fragment implements View.OnClickListener {
    private List<com.frederique.devaldo.domain.Calendar> calendarList;
    private TextView txtViewDistance;
    private com.frederique.devaldo.domain.Calendar c;
    private int position;

    public HomeScreenActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_screen, container, false);
        CardView cardView =(CardView) v.findViewById(R.id.card_view);
        cardView.setOnClickListener(this);
        try {
            ParseQuery<com.frederique.devaldo.domain.Calendar> query = ParseQuery.getQuery(com.frederique.devaldo.domain.Calendar.class);
            query.fromLocalDatastore();
            query.include("HomeTeam");
            query.include("AwayTeam");
            query.orderByAscending("Date");
            calendarList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        position = 0;
        while(calendarList.get(position).getDate().before(Calendar.getInstance().getTime()))
        {
          position++;
        }
         c = calendarList.get(position);
        TextView txtMatchInfoTextView = (TextView) v.findViewById(R.id.txtMatchInfo);
        txtMatchInfoTextView.setText(c.getHomeTeam().getName() + " - " + c.getAwayTeam().getName());
        TextView txtDateTextView = (TextView) v.findViewById(R.id.txtDate);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(c.getDate());
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        txtDateTextView.setText(day + "/" + month);
        txtViewDistance = (TextView) v.findViewById(R.id.txtDistanceTextView);
        new DistanceInfoTask().execute();

        return v;

    }

    @Override
    public void onClick(View v) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        //todo
        bundle.putInt("pos", position);
        CalendarFragment frag = new CalendarFragment();
        frag.setArguments(bundle);
        ft.replace(R.id.frame, frag);
        ft.addToBackStack(null);
        getFragmentManager().executePendingTransactions();
        ft.commit();



    }

    private class DistanceInfoTask extends AsyncTask<Void, Void, Void> {
        private String sDistance;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                StringBuilder urlString = new StringBuilder();
                urlString.append("http://maps.googleapis.com/maps/api/directions/json?origin=Groeningestraat%201,%209050%20Gentbrugge&destination=Victor%20Braeckmanlaan%20185,9040%20Gent&mode=bicycling&sensor=true");
                /*urlString.append("origin=");//from
                urlString.append("Groeningestraat 1, 9050 Gentbrugge");
                urlString.append("&destination=");//to
                urlString.append("Victor Braeckmanlaan 185,9040 Gent");
                urlString.append("&mode=bicycling&sensor=true");*/
                Log.d("xxx", "URL=" + urlString.toString());

                // get the JSON And parse it to get the directions data.
                HttpURLConnection urlConnection = null;
                URL url = null;

                url = new URL(urlString.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.connect();

                InputStream inStream = urlConnection.getInputStream();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));

                String temp, response = "";
                while ((temp = bReader.readLine()) != null) {
                    //Parse data
                    response += temp;
                }
                //Close the reader, stream & connection
                bReader.close();
                inStream.close();
                urlConnection.disconnect();
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray array = object.getJSONArray("routes");
                //Log.d("JSON","array: "+array.toString());

                //Routes is a combination of objects and arrays
                JSONObject routes = array.getJSONObject(0);
                //Log.d("JSON","routes: "+routes.toString());

                String summary = routes.getString("summary");
                //Log.d("JSON","summary: "+summary);

                JSONArray legs = routes.getJSONArray("legs");
                //Log.d("JSON","legs: "+legs.toString());

                JSONObject steps = legs.getJSONObject(0);
                //Log.d("JSON","steps: "+steps.toString());

                JSONObject distance = steps.getJSONObject("distance");
                //Log.d("JSON","distance: "+distance.toString());

                sDistance = distance.getString("text");
                sDistance = distance.getString("text");

            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            txtViewDistance.setText(sDistance);
            super.onPostExecute(result);
        }
    }
}
