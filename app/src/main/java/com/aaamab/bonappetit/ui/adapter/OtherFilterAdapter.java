package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.Cuisines;
import com.aaamab.bonappetit.data.FilterCity;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.data.MallItem;
import com.aaamab.bonappetit.data.ZoneItem;
import com.aaamab.bonappetit.ui.government.Selectedinter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class OtherFilterAdapter extends RecyclerView.Adapter<OtherFilterAdapter.MyViewHolder> {
    Context context;
    Selectedinter selectedinter;
    FilterData<FilterObj> data;
    AreaFilters areaMallData;
    private LayoutInflater mInflater;
    int type = 0;



    public OtherFilterAdapter(Context context, FilterData<FilterObj> data, int type, Selectedinter selectedinter, AreaFilters areaMallData) {
        this.context = context;
        this.areaMallData = areaMallData;
        this.selectedinter = selectedinter;
        this.type = type;
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OtherFilterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.filter_item, parent, false);
        return new OtherFilterAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OtherFilterAdapter.MyViewHolder holder, final int position) {
        if (type == 0) {
            holder.name.setText(data.getData().getCity().get(position).getName_en());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.imgSel.getVisibility() == View.VISIBLE) {
                        holder.imgSel.setVisibility(View.GONE);
                        selectedinter.onRemoved(data.getData().getCity().get(position).getName_en(), String.valueOf(data.getData().getZone().get(position).getId()));
                    } else {
                        holder.imgSel.setVisibility(View.VISIBLE);
                        selectedinter.onSelected(data.getData().getCity().get(position).getName_en(), String.valueOf(data.getData().getZone().get(position).getId()));
                    }
                }
            });
        }
        if (type == 1) {
            if (areaMallData != null) {
                Log.e(TAG, "onBindViewHolder: "+"Here" );
                if (areaMallData.getData().getZone().size() > 0) {
                    holder.name.setText(areaMallData.getData().getZone().get(position).getName_en());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (holder.imgSel.getVisibility() == View.VISIBLE) {
                                holder.imgSel.setVisibility(View.GONE);
                                selectedinter.onRemoved("area", String.valueOf(areaMallData.getData().getZone().get(position).getId()));
                            } else {
                                holder.imgSel.setVisibility(View.VISIBLE);
                                selectedinter.onSelected("area", String.valueOf(areaMallData.getData().getZone().get(position).getId()));
                            }
                        }
                    });
                } else {
                    Log.e(TAG, "onBindViewHolder: "+"Here" );
                    holder.name.setText(data.getData().getZone().get(position).getName_en());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (holder.imgSel.getVisibility() == View.VISIBLE) {
                                holder.imgSel.setVisibility(View.GONE);
                                selectedinter.onRemoved("area", String.valueOf(data.getData().getZone().get(position).getId()));
                            } else {
                                holder.imgSel.setVisibility(View.VISIBLE);
                                selectedinter.onSelected("area", String.valueOf(data.getData().getZone().get(position).getId()));
                            }
                        }
                    });
                }
            }else {
                Log.e(TAG, "onBindViewHolder: "+"Here" );
                holder.name.setText(data.getData().getZone().get(position).getName_en());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.imgSel.getVisibility() == View.VISIBLE) {
                            holder.imgSel.setVisibility(View.GONE);
                            selectedinter.onRemoved("area", String.valueOf(data.getData().getZone().get(position).getId()));
                        } else {
                            holder.imgSel.setVisibility(View.VISIBLE);
                            selectedinter.onSelected("area", String.valueOf(data.getData().getZone().get(position).getId()));
                        }
                    }
                });
            }

        }
        if (type == 2) {
            if (areaMallData != null) {
                if (areaMallData.getData().getMallss().size() > 0) {
                    holder.name.setText(areaMallData.getData().getMallss().get(position).getName_en());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (holder.imgSel.getVisibility() == View.VISIBLE) {
                                holder.imgSel.setVisibility(View.GONE);
                                selectedinter.onRemoved("mall", String.valueOf(areaMallData.getData().getMallss().get(position).getId()));
                            } else {
                                holder.imgSel.setVisibility(View.VISIBLE);
                                selectedinter.onSelected("mall", String.valueOf(areaMallData.getData().getMallss().get(position).getId()));
                            }
                        }
                    });
                } else {
                    holder.name.setText(data.getData().getMallss().get(position).getName_en());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (holder.imgSel.getVisibility() == View.VISIBLE) {
                                holder.imgSel.setVisibility(View.GONE);
                                selectedinter.onRemoved("mall", String.valueOf(data.getData().getMallss().get(position).getId()));
                            } else {
                                holder.imgSel.setVisibility(View.VISIBLE);
                                selectedinter.onSelected("mall", String.valueOf(data.getData().getMallss().get(position).getId()));
                            }
                        }
                    });
                }

            } else {
                holder.name.setText(data.getData().getMallss().get(position).getName_en());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.imgSel.getVisibility() == View.VISIBLE) {
                            holder.imgSel.setVisibility(View.GONE);
                            selectedinter.onRemoved("mall", String.valueOf(data.getData().getMallss().get(position).getId()));
                        } else {
                            holder.imgSel.setVisibility(View.VISIBLE);
                            selectedinter.onSelected("mall", String.valueOf(data.getData().getMallss().get(position).getId()));
                        }
                    }
                });
            }

        }
        if (type == 3) {
            holder.name.setText(data.getData().getCuisines().get(position).getName_en());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.imgSel.getVisibility() == View.VISIBLE) {
                        holder.imgSel.setVisibility(View.GONE);
                        selectedinter.onRemoved("cuis", String.valueOf(data.getData().getCuisines().get(position).getId()));
                    } else {
                        holder.imgSel.setVisibility(View.VISIBLE);
                        selectedinter.onSelected("cuis", String.valueOf(data.getData().getCuisines().get(position).getId()));
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (type == 0) {
            return data.getData().getCity().size();
        } else if (type == 1) {
            if (areaMallData != null) {
                return areaMallData.getData().getZone().size();
            } else {
                return data.getData().getZone().size();
            }

        } else if (type == 2) {
            if (areaMallData != null) {
                return areaMallData.getData().getMallss().size();
            } else {
                return data.getData().getMallss().size();
            }

        } else if (type == 3) {
            return data.getData().getCuisines().size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imgSel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            imgSel = itemView.findViewById(R.id.imgSelected);
        }
    }

    public void filterList(ArrayList<Cuisines> cuisines , ArrayList<MallItem> malls , ArrayList<ZoneItem> area) {
        /*if (type == 0) {

        } else*/ if (type == 1) {
            data.data.zone = area;
            notifyDataSetChanged();

        } else if (type == 2) {
            data.data.mallss = malls;
            notifyDataSetChanged();
        } else if (type == 3) {
            data.data.cuisines = cuisines;
            notifyDataSetChanged();
        }

    }
}