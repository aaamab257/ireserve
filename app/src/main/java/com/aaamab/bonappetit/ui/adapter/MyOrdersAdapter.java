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
import com.aaamab.bonappetit.data.ReOrder;
import com.aaamab.bonappetit.ui.orderFoods.OrderFoodsScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.squareup.picasso.Picasso;

import okhttp3.RequestBody;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    AllMyOrders data;

    public MyOrdersAdapter(Context context, AllMyOrders data) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.my_orders_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(data.getData().get(position).getResData().getName());
        Picasso.with(context).load(data.getData().get(position).getResData().getMain_image()).into(holder.resImage);
        holder.price.setText(context.getString(R.string.price_ada)+ data.getData().get(position).getPrice() + " KD");
        holder.date.setText(context.getString(R.string.date_dots) + data.getData().get(position).getCreated_at());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticMethods.foods = data.getData().get(position).getOrder_food();
                IntentUtilies.openActivity(context, OrderFoodsScreen.class);
            }
        });

        holder.reOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callReOrderApi(data.getData().get(position).getId());
            }
        });
    }

    private void callReOrderApi(int id) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            RequestBody body = null;
            try {
                body = MainApiBody.reOrder(id);
            } catch (Exception e) {

            }
            MainApi.reOrder(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<ReOrder>() {
                @Override
                public void onSuccess(ConnectionResponse<ReOrder> connectionResponse) {

                    ToastUtil.showSuccessToast(context, R.string.re_success);

                }

                @Override
                public void onFail(Throwable throwable) {
                    ToastUtil.showSuccessToast(context, R.string.re_fail);
                }
            });
        } else {
            ToastUtil.showSuccessToast(context, R.string.noConnection);
        }
    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, size, quantity, date, price;
        ImageView resImage, reOrder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reOrder = itemView.findViewById(R.id.imageView12);
            price = itemView.findViewById(R.id.price);
            size = itemView.findViewById(R.id.size);
            name = itemView.findViewById(R.id.food_name);
            resImage = itemView.findViewById(R.id.image_res);
            quantity = itemView.findViewById(R.id.quantity);
            date = itemView.findViewById(R.id.date);
        }
    }
}