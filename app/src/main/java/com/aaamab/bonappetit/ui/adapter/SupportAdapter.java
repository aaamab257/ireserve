package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.SupportArray;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    SupportArray array ;


    public SupportAdapter(Context context,SupportArray array) {
        this.context = context;
        this.array = array ;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.support_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtQ.setText(array.data.get(position).getQ());
        holder.txtAns.setText(array.data.get(position).getA());
    }

    @Override
    public int getItemCount() {
        return array.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtQ , txtAns ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQ  = itemView.findViewById(R.id.textView11);
            txtAns  = itemView.findViewById(R.id.textView16);
        }
    }
}
