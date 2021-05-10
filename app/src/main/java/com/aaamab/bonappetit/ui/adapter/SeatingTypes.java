package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.SeatingArray;

import java.util.ArrayList;

public class SeatingTypes extends RecyclerView.Adapter<SeatingTypes.MyViewHolder> {
    Context context ;
    ArrayList<SeatingArray> data ;
    private LayoutInflater mInflater;

    public SeatingTypes(Context context, ArrayList<SeatingArray> data) {
        this.context = context;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.seating_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }else {
            return 0 ;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}
