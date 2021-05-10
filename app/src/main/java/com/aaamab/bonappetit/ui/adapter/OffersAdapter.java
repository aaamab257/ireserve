package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.OffersData;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.squareup.picasso.Picasso;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {
    String[] resName = {"Restaurant name 1", "Restaurant name 2", "Restaurant name 3", "Restaurant name 4"};
    String[] mealNameS = {"Combo 1", "Combo 2", "Combo 3", "Combo 4"};
    String[] foodType2 = {"Food Type", "Food Type", "Food Type", "Food Type"};
    String[] space = {"10 km", "20 km", "05 km", "03 km"};

    private LayoutInflater mInflater;
    Context context;
    OffersData data ;

    public OffersAdapter(Context context,OffersData data) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.offers_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mealName.setText(data.getData().get(position).getCategory().getName());
        holder.name.setText(data.getData().get(position).getRest().getName());
        holder.currentPrice.setText(data.getData().get(position).getOffer_price() +" KD");
        holder.prePrice.setText(data.getData().get(position).getPrice() +" KD");
        holder.prePrice.setPaintFlags(holder.prePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Picasso.with(context).load(data.getData().get(position).getImg()).into(holder.resImage);
        holder.offerTime.setText(context.getString(R.string.for_text)+data.getData().get(position).getDays() +context.getString(R.string.days_text));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainScreen) context).goToResDetails(data.getData().get(position).getRestaurant_id());
                StaticMethods.resID = data.getData().get(position).getRestaurant_id();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mealName, currentPrice , prePrice , offerTime;
        ImageView share, resImage , fav;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_resName);
            mealName = itemView.findViewById(R.id.txt_mealName);
            currentPrice = itemView.findViewById(R.id.txt_price);
            prePrice = itemView.findViewById(R.id.txt_pervious);
            offerTime = itemView.findViewById(R.id.txt_offerTime);
            share = itemView.findViewById(R.id.share_icon);
            resImage = itemView.findViewById(R.id.image_res);
            fav = itemView.findViewById(R.id.fav_heart);
        }
    }
}
