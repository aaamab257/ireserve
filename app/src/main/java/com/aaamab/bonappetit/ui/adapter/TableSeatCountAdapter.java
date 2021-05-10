package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.ui.dineinOrder.onCountSelected;

public class TableSeatCountAdapter extends RecyclerView.Adapter<TableSeatCountAdapter.MyViewHolder> {
    String[] space = {"1", "2", "3", "4" , "5", "6", "7", "8" , "9" , "10"};
    private LayoutInflater mInflater;
    Context context;
    int pos = 0 ;
    onCountSelected onCountSelected ;
    public TableSeatCountAdapter(Context context , onCountSelected onCountSelected) {
        this.context = context;
        this.onCountSelected = onCountSelected;
        this.mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.seats_selector_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(space[position]);
    }

    @Override
    public int getItemCount() {
        return space.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout selector ;
        TextView txt_Seats ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            selector = itemView.findViewById(R.id.rel_selector);
            txt_Seats = itemView.findViewById(R.id.txt_Num);
        }
        void bind(final String seat) {
            if (pos == -1) {
                selector.setBackground(context.getResources().getDrawable(R.drawable.rounded_style));
            } else {
                if (pos == getAdapterPosition()) {
                    selector.setBackground(context.getResources().getDrawable(R.drawable.rounded_normal_style));
                } else {
                    selector.setBackground(context.getResources().getDrawable(R.drawable.rounded_style));
                }
            }

            txt_Seats.setText(seat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCountSelected.onSelected(seat);
                    selector.setBackground(context.getResources().getDrawable(R.drawable.rounded_normal_style));
                    if (pos != getAdapterPosition()) {
                        notifyItemChanged(pos);
                        pos = getAdapterPosition();
                    }
                }
            });
        }
    }
}
