package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.ImagesItem;
import com.aaamab.bonappetit.data.RestArray;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    ArrayList<ImagesItem> images ;


    public ImagesAdapter(Context context, ArrayList<ImagesItem> images) {
        this.context = context;
        this.images = images;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.MyViewHolder holder, int position) {
        Picasso.with(context).load(images.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_res);
        }
    }
}
