package com.aaamab.bonappetit.ui.adapter;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FavoritesData;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    FavoritesData data;


    public FavoriteAdapter(Context context, FavoritesData data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.favorite_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.MyViewHolder holder, final int position) {
        if (data.getFa().get(position).getRest().types != null) {
            if (data.getFa().get(position).getRest().getTypes().size() == 2) {
                holder.foodType1.setText(data.getFa().get(position).getRest().getTypes().get(0).getName());
                holder.foodType2.setText(data.getFa().get(position).getRest().getTypes().get(1).getName());
            } else if (data.getFa().get(position).getRest().getTypes().size() == 1) {
                holder.foodType1.setText(data.getFa().get(position).getRest().getTypes().get(0).getName());
                holder.two.setVisibility(View.GONE);
            } else if (data.getFa().get(position).getRest().getTypes().size() == 0) {
                holder.two.setVisibility(View.GONE);
                holder.three.setVisibility(View.GONE);
            }
        } else {
            holder.two.setVisibility(View.GONE);
            holder.three.setVisibility(View.GONE);
        }

        StaticMethods.printJson("obj", data.getFa().get(position).getRest().getTypes());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainScreen) context).goToResDetails(data.getFa().get(position).getRest().getId());
                StaticMethods.resID = data.getFa().get(position).getRest().getId();
            }
        });
        //holder.dis.setText(data.getFa().get(position).getRest().getDescription_en());
        holder.name.setText(data.getFa().get(position).getRest().getName());
        Picasso.with(context).load(data.getFa().get(position).getRest().getMain_image()).into(holder.resImage);
        holder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.getFa().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, dis, foodType1, foodType2;
        ImageView bin, resImage;
        RelativeLayout two, three;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            two = itemView.findViewById(R.id.cardView2);
            three = itemView.findViewById(R.id.cardView3);
            name = itemView.findViewById(R.id.res_name);
            resImage = itemView.findViewById(R.id.image_res);
            //dis = itemView.findViewById(R.id.fav_dis);
            foodType1 = itemView.findViewById(R.id.fav_type1);
            foodType2 = itemView.findViewById(R.id.fav_type2);
            bin = itemView.findViewById(R.id.imageView2);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }

    public void removeItem(int position) {
        data.getFa().remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    /*public void restoreItem(ClipData.Item item, int position) {
        data.getFa().add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }*/
}
