package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.BookingArray;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.ui.boooking.BookingScreen;
import com.aaamab.bonappetit.ui.boooking.OnTerms;
import com.squareup.picasso.Picasso;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    BookingArray data ;
    OnTerms clicked ;


    public BookingsAdapter(Context context, BookingArray data, OnTerms clicked) {
        this.context = context;
        this.data = data;
        this.clicked = clicked;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.booking_item, parent, false);
        return new BookingsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsAdapter.MyViewHolder holder, final int position) {
        holder.at.setText(context.getString(R.string.at)+data.getBookingItems().get(position).getDate() );
        holder.name.setText(data.getBookingItems().get(position).getRestaurant().getName());
        holder.count.setText(context.getString(R.string.table_for)+data.getBookingItems().get(position).getCount()+context.getString(R.string.person));
        Picasso.with(context).load(data.getBookingItems().get(position).getRestaurant().getMain_image()).into(holder.resImage);
        /*if(position == 0){
            holder.terms.setVisibility(View.VISIBLE);

        }else {
            holder.terms.setVisibility(View.GONE);
        }*/
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BookingScreen)context).onCall(data.getBookingItems().get(position).getRestaurant().getPhone());
            }
        });
        holder.terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onClickTerms(true);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onEdit(true);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onCancel(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getBookingItems().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView at,name, count ;
        ImageView  resImage ;
        Button cancel , edit ;
        RelativeLayout call ;
        LinearLayout terms ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            call = itemView.findViewById(R.id.btnCall);
            cancel = itemView.findViewById(R.id.btnCancel);
            edit = itemView.findViewById(R.id.btnEdit);
            at = itemView.findViewById(R.id.txtCreated_date);
            name = itemView.findViewById(R.id.txtResName);
            terms = itemView.findViewById(R.id.linearLayout6);
            resImage = itemView.findViewById(R.id.txtResImage);
            count = itemView.findViewById(R.id.txtPersonNo);

        }
    }


}
