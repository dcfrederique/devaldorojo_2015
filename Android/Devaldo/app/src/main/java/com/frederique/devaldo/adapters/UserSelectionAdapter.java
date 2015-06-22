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
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_user_item_layout_2, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int resID = context.getResources().getIdentifier(usersData[position].getName().split(" ")[0].toLowerCase(), "drawable", context.getPackageName());
        Picasso.with(context).load(resID).error(R.drawable.logo).into(viewHolder.imageView);

    }
    // inner class to hold a reference to each item of RecyclerView 
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView imageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageView = (CircleImageView) itemLayoutView.findViewById(R.id.profile_image);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return usersData.length;
    }
}