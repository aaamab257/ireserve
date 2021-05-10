package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FoodsItem;

import java.util.ArrayList;

public class FoodPaymentAdapter extends RecyclerView.Adapter<FoodPaymentAdapter.MyViewHolder> {
    Context context;
    ArrayList<FoodsItem> getOrderFood ;
    private LayoutInflater mInflater;
    public FoodPaymentAdapter(Context context, ArrayList<FoodsItem> getOrderFood) {
        this.context = context;
        this.getOrderFood = getOrderFood;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.food_payment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(getOrderFood.get(position).getName());
        holder.price.setText(getOrderFood.get(position).getPrice()+" KD");
        holder.unit.setText(getOrderFood.get(position).getPrice()+"");
        holder.quantity.setText(getOrderFood.get(position).getAmount()+"");
    }

    @Override
    public int getItemCount() {
        return getOrderFood.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name , quantity , unit , price ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            quantity = itemView.findViewById(R.id.txtQuantity);
            unit = itemView.findViewById(R.id.txtUnit);
            price = itemView.findViewById(R.id.txtPrice);
        }
    }
}
