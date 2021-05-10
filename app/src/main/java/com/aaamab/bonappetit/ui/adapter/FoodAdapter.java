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
import com.aaamab.bonappetit.data.AllMyOrders;
import com.aaamab.bonappetit.data.OrdersFoodItem;
import com.aaamab.bonappetit.data.PivotObj;
import com.aaamab.bonappetit.data.ReOrder;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.RequestBody;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    ArrayList<OrdersFoodItem<PivotObj>> data ;

    public FoodAdapter(Context context, ArrayList<OrdersFoodItem<PivotObj>> data) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.my_orders_food_item, parent, false);
        return new FoodAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.orderDetails.setText(context.getString(R.string.qty)+data.get(position).getPivot().getAmount());
        holder.prePrice.setText(context.getString(R.string.total)+data.get(position).getPrice()+" KD");
        Picasso.with(context).load(data.get(position).getImage()).into(holder.resImage);
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, dis, orderDetails, foodType2 , price , prePrice;
        ImageView resImage ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.txtCurrent);
            prePrice = itemView.findViewById(R.id.quantity);
            orderDetails = itemView.findViewById(R.id.size);
            name = itemView.findViewById(R.id.food_name);
            resImage = itemView.findViewById(R.id.image_res);
        }
    }
}