package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AddFoodArray;
import com.aaamab.bonappetit.data.MyOrdersArray;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.squareup.picasso.Picasso;

import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    MyOrdersArray data;
    Context context;
    String type ;
    public MyOrderAdapter(Context context, MyOrdersArray data ,String type) {
        this.data = data;
        this.type = type ;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pickup_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.orderDetails.setText(data.getMyOrders().get(position).getFood().getDescription());
        holder.name.setText(data.getMyOrders().get(position).getFood().getName());
        if( data.getMyOrders().get(position).getFood().getIs_offer().equals("0")){
            holder.price.setText(data.getMyOrders().get(position).getFood().getPrice()+" KD");
            holder.pre.setVisibility(View.GONE);
        }else {
            holder.price.setText(data.getMyOrders().get(position).getFood().getOffer_price()+" KD");
            holder.pre.setText(data.getMyOrders().get(position).getFood().getPrice()+" KD");
            holder.pre.setPaintFlags(holder.pre.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        //holder.price.setText(data.getMyOrders().get(position).getFood().getPrice() + " KD");
        Picasso.with(context).load(data.getMyOrders().get(position).getFood().getImage()).into(holder.resImage);
        holder.quan.setText(data.getMyOrders().get(position).getAmount() + "");
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qua = holder.quan.getText().toString();
                int quani = Integer.parseInt(qua);
                quani++;
                holder.quan.setText(quani + "");
                String quass = holder.quan.getText().toString();
                int quaniss = Integer.parseInt(quass);
                addFood(data.getMyOrders().get(position).getFood().getId(), position, 1 , data.myOrders.get(position).getFood().getRestaurant_id() , type);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qua = holder.quan.getText().toString();
                Log.e(TAG, "onClick: " + "here1");
                int quani = Integer.parseInt(qua);

                if (quani == 1) {
                    Log.e(TAG, "onClick: Pos" + position);
                    removeFood(data.getMyOrders().get(position).getFood().getId(), position, 1);
                    removeAt(position);
                } else {
                    quani--;
                    holder.quan.setText(quani + "");
                    holder.remove.setEnabled(true);
                    removeFood(data.getMyOrders().get(position).getFood().getId(), position, 1);
                }

            }
                /*Log.e(TAG, "onClick: " + "here2");
                String quas = holder.quan.getText().toString();
                int quanis = Integer.parseInt(quas);*/


        });
    }

    public void removeAt(int position) {
        data.getMyOrders().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.getMyOrders().size());
    }

    public void clear() {
        int size = data.getMyOrders().size();
        data.getMyOrders().clear();
        notifyItemRangeRemoved(0, size);
    }

    private void addFood(int id, int position, int quaniss , int resId , String type) {
        RequestBody body = null;
        try {
            body = MainApiBody.addFood(id, quaniss ,resId , type) ;
        } catch (Exception e) {

        }
        MainApi.addFood(StaticMethods.userData.getApi_token(),LocaleManager.getLanguage(context), body, new ConnectionListener<AddFoodArray>() {
            @Override
            public void onSuccess(ConnectionResponse<AddFoodArray> connectionResponse) {
                if (connectionResponse.data.getAddFoodItems().size() > 0) {
                    ToastUtil.showSuccessToast(context, connectionResponse.data.getMsg());
                } else {
                    ToastUtil.showErrorToast(context, connectionResponse.data.getMsg());
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                ToastUtil.showErrorToast(context, throwable.getMessage());
            }
        });
    }

    private void removeFood(int id, int position, int quaniss) {
        RequestBody body = null;
        try {
            body = MainApiBody.addAndRemoveFood(id, quaniss);
        } catch (Exception e) {

        }

        MainApi.removeFood(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<RemoveFood<RemoveFoodOBJ>>() {
            @Override
            public void onSuccess(ConnectionResponse<RemoveFood<RemoveFoodOBJ>> connectionResponse) {
                if (connectionResponse.data.da.isStatus()) {
                    Log.e(TAG, "onClick: " + "here3");
                    ToastUtil.showSuccessToast(context, connectionResponse.data.getMsg());
                } else {
                    Log.e(TAG, "onClick: " + "here3");
                    ToastUtil.showErrorToast(context, connectionResponse.data.getMsg());
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                ToastUtil.showErrorToast(context, throwable.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getMyOrders().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quan, orderDetails, foodType2, price,pre;
        ImageView add, resImage, remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pre = itemView.findViewById(R.id.txtPre);
            price = itemView.findViewById(R.id.quantity);
            orderDetails = itemView.findViewById(R.id.size);
            name = itemView.findViewById(R.id.food_name);
            resImage = itemView.findViewById(R.id.image_res);
            add = itemView.findViewById(R.id.add_image);
            remove = itemView.findViewById(R.id.remove_quantity);
            quan = itemView.findViewById(R.id.his_quantity);
        }
    }
}
