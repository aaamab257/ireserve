package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FilterCity;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.ResturantsData;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.ui.searchResult.SearchResult;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DineInAdapter extends RecyclerView.Adapter<DineInAdapter.MyViewHolder> {
    String[] resName = {"Restaurant name 1", "Restaurant name 2", "Restaurant name 3", "Restaurant name 4"};
    private LayoutInflater mInflater;
    Context context;
    RestArray array;
    ImagesAdapter adapter;
    String type;
    String tt = "";
    SeatingTypes seatingTypes ;

    public DineInAdapter(Context context, RestArray array, String type, String tt) {
        this.context = context;
        this.array = array;
        this.mInflater = LayoutInflater.from(context);
        this.type = type;
        this.tt = tt;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.dinein_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.resName.setText(array.getData().get(position).getName());
        seatingTypes = new SeatingTypes(context,array.getData().get(position).seating);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.seatingType.setLayoutManager(layoutManager);
        holder.seatingType.setAdapter(seatingTypes);
        /*adapter = new ImagesAdapter(context , array.getData().get(position).getImages());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.view.setLayoutManager(layoutManager);
        holder.view.setAdapter(adapter);*/
        Picasso.with(context).load(array.getData().get(position).getMain_image()).into(holder.view);
        holder.foodType.setText(array.getData().get(position).getTypes().get(0).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tt.equals("M")) {
                    ((MainScreen) context).goToResDetails(array.getData().get(position).getId());
                    StaticMethods.resID = array.getData().get(position).getId();
                } else {
                    ((SearchResult) context).goToResDetails(array.getData().get(position).getId());
                    StaticMethods.resID = array.getData().get(position).getId();
                }
                if (type.equals("P")) {
                    StaticMethods.type = "P";
                } else if (type.equals("C")) {
                    StaticMethods.type = "C";
                } else if (type.equals("D")) {
                    StaticMethods.type = "D";
                }

            }
        });
        /*if (array.getData().get(position).getTypes() != null){
            holder.foodType.setText(array.getData().get(position).getTypes().get(0).getName());
        }*/
        if (type.equals("P")) {
            holder.res.setVisibility(View.GONE);

        }
        if (type.equals("C")) {
            holder.res.setVisibility(View.GONE);
        }
        holder.res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticMethods.resID = array.getData().get(position).getId();
                if (type.equals("P")) {
                    ((MainScreen) context).openPickUp();

                } else if (type.equals("C")) {
                    ((MainScreen) context).openCurbside();

                } else if (type.equals("D")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", array.getData().get(position).getName());
                    bundle.putString("image", array.getData().get(position).getMain_image());
                    ((MainScreen) context).openDineIn(bundle);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return array.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView discount;
        RatingBar ratingBar;
        TextView resName, foodType, seatingTypes;
        ImageView view;
        Button res;
        RecyclerView seatingType ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seatingType = itemView.findViewById(R.id.txt_offerTime);
            resName = itemView.findViewById(R.id.txt_resName);
            view = itemView.findViewById(R.id.image_res);
            foodType = itemView.findViewById(R.id.txt_foodType);
            res = itemView.findViewById(R.id.btn_reserve);
        }
    }

    public void filterList(ArrayList<ResturantsData> resturantsData) {
        array.data = resturantsData;
        notifyDataSetChanged();
    }
}
