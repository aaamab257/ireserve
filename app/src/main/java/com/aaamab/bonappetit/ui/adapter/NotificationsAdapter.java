package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.NotificationArray;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder> {

    Context context ;
    private LayoutInflater mInflater;
    NotificationArray array ;

    public NotificationsAdapter(Context context ,NotificationArray array) {
        this.context = context;
        this.array =array;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notification_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.MyViewHolder holder, int position) {
        holder.con.setText(array.getNotificationItems().get(position).getMessage());
        holder.dates.setText(array.getNotificationItems().get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return array.getNotificationItems().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView con , dates ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            con = itemView.findViewById(R.id.txt_content);
            dates = itemView.findViewById(R.id.txtDate);
        }
    }
}
