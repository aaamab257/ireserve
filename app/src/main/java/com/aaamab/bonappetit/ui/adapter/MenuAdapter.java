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
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.ui.menus.onImageClicked;
import com.squareup.picasso.Picasso;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    FoodData data ;
    onImageClicked clicked;

    public MenuAdapter(Context context, FoodData data ,onImageClicked clicked) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
        this.clicked = clicked ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(data.getFa().get(position).getName());
        holder.orderDetails.setText(data.getFa().get(position).getDescription());
        if(data.getFa().get(position).getIs_offer().equals("1")){
            holder.price.setText(data.getFa().get(position).getOffer_price()+" KD");
            holder.prePrice.setText(data.getFa().get(position).getPrice()+" KD");
            holder.prePrice.setPaintFlags(holder.prePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.price.setText(data.getFa().get(position).getPrice()+" KD");
            holder.prePrice.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onClicked(data.getFa().get(position).getImage());
            }
        });
        Picasso.with(context).load(data.getFa().get(position).getImage()).into(holder.resImage);
    }

    @Override
    public int getItemCount() {
        return data.getFa().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, dis, orderDetails, foodType2 , price , prePrice;
        ImageView add, resImage , remove;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.txtCurrent);
            prePrice = itemView.findViewById(R.id.quantity);
            orderDetails = itemView.findViewById(R.id.size);
            name = itemView.findViewById(R.id.food_name);
            resImage = itemView.findViewById(R.id.image_res);
            add = itemView.findViewById(R.id.add_image);
            remove = itemView.findViewById(R.id.remove_quantity);
        }
    }
}
