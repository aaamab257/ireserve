package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.PaymentMoreData;
import com.aaamab.bonappetit.ui.paymentOrderDetails.PaymentOrderDetails;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

public class PaymentDetailsAdapter extends RecyclerView.Adapter<PaymentDetailsAdapter.MyViewHandler> {
    Context context;
    PaymentMoreData data ;
    private LayoutInflater mInflater;

    public PaymentDetailsAdapter(Context context, PaymentMoreData data) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PaymentDetailsAdapter.MyViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.payment_items, parent, false);
        return new MyViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentDetailsAdapter.MyViewHandler holder,final int position) {
        holder.date.setText(data.data.get(position).getCreated_at());
        Log.e(TAG, "onBindViewHolder: "+data.data.get(position).getType() );
        String t = data.data.get(position).getType() ;
        if (t != null){
            if (t.equals("C")){
                holder.type.setText(" Curbside ");
            }else if (t.equals("D")){
                holder.type.setText(" Dian In ");
            }else if (t.equals("P")){
                holder.type.setText(" Pickup ");
            }

        }


        holder.resName.setText(data.data.get(position).getRestaurant().getName());

        Picasso.with(context).load(data.data.get(position).getRestaurant().getMain_image()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id",data.data.get(position).getRestaurant_id());
                b.putString("date" , data.data.get(position).getCreated_at());
                b.putFloat("total" , data.data.get(position).getTotal_amount());
                b.putFloat("services" , data.data.get(position).getServices_charge());
                b.putFloat("Subtotal" , data.data.get(position).getSubtotal());
                StaticMethods.getOrderFood = data.data.get(position).getOrderFood();
                IntentUtilies.openActivityWithBundle(context, PaymentOrderDetails.class,b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.data.size();
    }

    public class MyViewHandler extends RecyclerView.ViewHolder {
        ImageView img ;
        TextView date , resName , type ;
        public MyViewHandler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.res_image);
            date = itemView.findViewById(R.id.txtDate);
            resName = itemView.findViewById(R.id.txtResName);
            type = itemView.findViewById(R.id.txtType);
        }
    }
}
