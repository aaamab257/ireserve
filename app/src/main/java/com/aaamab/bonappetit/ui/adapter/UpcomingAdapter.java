package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.ResReviews;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.data.UserReviews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder> {
    Upcomming upcomming;

    private LayoutInflater mInflater;
    Context context;

    public UpcomingAdapter(Context context, Upcomming upcomming) {
        this.context = context;
        this.upcomming = upcomming ;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UpcomingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.upcoming_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(upcomming.data.get(position).getUpcoming());
        holder.details.setText(upcomming.data.get(position).getName_en());
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+upcomming.data.size() );
        return upcomming.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, details ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtUpcoming);
            details = itemView.findViewById(R.id.txtBottomDetails);

        }
    }
}