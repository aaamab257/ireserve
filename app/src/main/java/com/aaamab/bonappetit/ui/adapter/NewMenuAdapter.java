package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FoodsItem;
import com.aaamab.bonappetit.data.RestMenu;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class NewMenuAdapter extends RecyclerView.Adapter<NewMenuAdapter.MyViewHolder> {
    Context context ;
    RestMenu restMenu ;
    private LayoutInflater mInflater;
    PickupAdapter adapter ;
    int pos ;
    String type ;
    public NewMenuAdapter(Context context, RestMenu restMenu , String type , int pos ) {
        Log.e(TAG, "NewMenuAdapter: "+restMenu.getData().size() );
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.restMenu = restMenu;
        this.type = type;
        this.pos = pos;
    }

    @NonNull
    @Override
    public NewMenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.new_menu_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewMenuAdapter.MyViewHolder holder, int position) {
        holder.name.setText(restMenu.data.get(position).getName());
        if (restMenu.getData().get(position).getFoods().size() == 0){
            holder.name.setVisibility(View.GONE);
        }else {

            adapter = new PickupAdapter(context,null,type,restMenu.data.get(position).getFoods() , pos);
            holder.recFoods.setLayoutManager(new LinearLayoutManager(context));
            holder.recFoods.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {
        return restMenu.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        RecyclerView recFoods ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView. findViewById(R.id.txtCategory);
            recFoods = itemView. findViewById(R.id.recFoods);

        }
    }
}
