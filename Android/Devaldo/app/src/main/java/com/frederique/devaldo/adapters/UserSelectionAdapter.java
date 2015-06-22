package com.frederique.devaldo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frederique.devaldo.R;
import com.frederique.devaldo.domain.UserData;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserSelectionAdapter extends RecyclerView.Adapter<UserSelectionAdapter.ViewHolder> {
    private UserData[] usersData;
    private Context context;

    public UserSelectionAdapter(UserData[] usersData, Context applicationContext) {
        this.usersData = usersData;
        this.context = applicationContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserSelectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_user_item_layout, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        /*Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Lobster 1.4.otf");
        viewHolder.txtViewTitle.setTypeface(type);*/
        viewHolder.txtViewTitle.setText(usersData[position].getName());
        int resID = context.getResources().getIdentifier(usersData[position].getName().split(" ")[0].toLowerCase(), "drawable", context.getPackageName());
        //viewHolder.imageView.setImageResource(resID);

        Picasso.with(context).load(resID).error(R.drawable.logo).into(viewHolder.imageView);



    }
    // inner class to hold a reference to each item of RecyclerView 
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.USER_name);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.profile_image_start);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return usersData.length;
    }
}