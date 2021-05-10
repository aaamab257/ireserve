package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FilterCity;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.ui.government.Selectedinter;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {
    Context context ;
    Selectedinter selectedinter ;
    FilterData<FilterObj> data ;
    private LayoutInflater mInflater;
    int type = 0 ;

    public FilterAdapter(Context context, FilterData<FilterObj> data, int type,Selectedinter selectedinter) {
        this.context = context;
        this.selectedinter = selectedinter;
        this.type = type ;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.filter_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if(type == 0){
            holder.name.setText(data.getData().getCity().get(position).getName_en());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( holder.imgSel.getVisibility() == View.VISIBLE){
                        holder.imgSel.setVisibility(View.GONE);
                        selectedinter.onRemoved(data.getData().getCity().get(position).getName_en(),String.valueOf(data.getData().getCity().get(position).getId()));
                    }else {
                        holder.imgSel.setVisibility(View.VISIBLE);
                        selectedinter.onSelected(data.getData().getCity().get(position).getName_en(),String.valueOf(data.getData().getCity().get(position).getId()));
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (type == 0){
            return data.getData().getCity().size();
        }else if(type == 1){
            return data.getData().getZone().size();
        }else if(type == 2){
            return data.getData().getMallss().size();
        }else {
            return data.getData().getCuisines().size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        ImageView imgSel ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            imgSel= itemView.findViewById(R.id.imgSelected);
        }
    }

    public void filterList(ArrayList<FilterCity> filteredList) {
        data.data.city = filteredList;
        notifyDataSetChanged();
    }
}
