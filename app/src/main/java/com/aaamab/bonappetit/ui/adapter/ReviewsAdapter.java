package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.ResReviews;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.UserReviews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {
    ArrayList<ResReviews<UserReviews>> reviews;

    private LayoutInflater mInflater;
    Context context;

    public ReviewsAdapter(Context context, ArrayList<ResReviews<UserReviews>> restruant) {
        this.context = context;
        this.reviews = restruant ;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.review_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.comment.setText(reviews.get(position).getContent());
        holder.name.setText(reviews.get(position).user.getName());
        if (reviews.get(position).user.getImage() == null){
            holder.img.setImageResource(R.drawable.avatar_logo);
        }else{
            Picasso.with(context).load(reviews.get(position).user.getImage()).into(holder.img);
        }

        holder.date.setText(reviews.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, comment, date;
        ImageView img ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textView12);
            name = itemView.findViewById(R.id.txt_userName);
            comment = itemView.findViewById(R.id.txt_comment);
            img = itemView.findViewById(R.id.img_user);
        }
    }
}
