package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AddFoodArray;
import com.aaamab.bonappetit.data.AddFoodCheck;
import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.FoodsItem;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class PickupAdapter extends RecyclerView.Adapter<PickupAdapter.MyViewHolder> {


    private LayoutInflater mInflater;
    FoodData data;
    Context context;
    String type;
    ArrayList<FoodsItem> menuData ;

    public PickupAdapter(Context context, FoodData data, String type ,ArrayList<FoodsItem> menuData) {
        this.mInflater = LayoutInflater.from(context);
        this.type = type;
        this.context = context;
        this.data = data;
        this.menuData = menuData;
    }

    @NonNull
    @Override
    public PickupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pickup_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PickupAdapter.MyViewHolder holder, final int position) {
        holder.orderDetails.setText(menuData.get(position).getDescription());
        holder.name.setText(menuData.get(position).getName());
        if (menuData.get(position).getIs_offer().equals("0")) {
            holder.price.setText(menuData.get(position).getPrice() + " KD");
            holder.pre.setVisibility(View.GONE);
        } else {
            holder.price.setText(menuData.get(position).getOffer_price() + " KD");
            holder.pre.setText(menuData.get(position).getPrice() + " KD");
            holder.pre.setPaintFlags(holder.pre.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        Picasso.with(context).load(menuData.get(position).getImage()).into(holder.resImage);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qua = holder.quan.getText().toString();
                int quani = Integer.parseInt(qua);
                quani++;
                holder.quan.setText(quani + "");
                String quass = holder.quan.getText().toString();
                int quaniss = Integer.parseInt(quass);

                checkFood(menuData.get(position).getId(), position, 1, menuData.get(position).getRestaurant_id(), type);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qua = holder.quan.getText().toString();
                int quani = Integer.parseInt(qua);
                if (quani == 0) {
                    holder.remove.setEnabled(false);
                } else if (quani > 0) {
                    quani--;
                    holder.quan.setText(quani + "");
                    holder.remove.setEnabled(false);
                    removeFood(menuData.get(position).getId(), position, 1);
                }
                String quas = holder.quan.getText().toString();
                int quanis = Integer.parseInt(quas);


            }
        });
    }

    public void addFood(int id, int position, int quaniss, int resId, String type) {
        RequestBody body = null;
        try {
            body = MainApiBody.addFood(id, quaniss, resId, type);
        } catch (Exception e) {

        }
        MainApi.addFood(StaticMethods.userData.getApi_token() , LocaleManager.getLanguage(context), body, new ConnectionListener<AddFoodArray>() {
            @Override
            public void onSuccess(ConnectionResponse<AddFoodArray> connectionResponse) {
                if (connectionResponse.data.getAddFoodItems().size() > 0) {
                    ToastUtil.showSuccessToast(context, connectionResponse.data.getMsg());
                } else {
                    ToastUtil.showErrorToast(context, connectionResponse.data.getMsg());
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                ToastUtil.showErrorToast(context, throwable.getMessage());
            }
        });
    }

    private void checkFood(final int id, final int position, final int quaniss, final int resId, final String type) {
        RequestBody body = null;
        try {
            body = MainApiBody.addFood(id, quaniss, resId, type);
        } catch (Exception e) {

        }
        MainApi.addFoodCheck(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<AddFoodCheck>() {
            @Override
            public void onSuccess(ConnectionResponse<AddFoodCheck> connectionResponse) {
                if (connectionResponse.data.getData().isStatus()) {
                    addFood(id, position, quaniss, resId, type);

                } else {
                    openAlert(id, position, quaniss, resId, type);
                    //ToastUtil.showErrorToast(context, connectionResponse.data.getMsg());
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                ToastUtil.showErrorToast(context, throwable.getMessage());
            }
        });
    }

    private void removeFood(int id, int position, int quaniss) {
        RequestBody body = null;
        try {
            body = MainApiBody.addAndRemoveFood(id, quaniss);
        } catch (Exception e) {

        }

        MainApi.removeFood(StaticMethods.userData.getApi_token(),LocaleManager.getLanguage(context), body, new ConnectionListener<RemoveFood<RemoveFoodOBJ>>() {
            @Override
            public void onSuccess(ConnectionResponse<RemoveFood<RemoveFoodOBJ>> connectionResponse) {
                if (connectionResponse.data.da.isStatus()) {
                    ToastUtil.showSuccessToast(context, connectionResponse.data.getMsg());
                } else {
                    ToastUtil.showErrorToast(context, connectionResponse.data.getMsg());
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                ToastUtil.showErrorToast(context, throwable.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quan, orderDetails, foodType2, price, pre;
        ImageView add, resImage, remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pre = itemView.findViewById(R.id.txtPre);
            price = itemView.findViewById(R.id.quantity);
            orderDetails = itemView.findViewById(R.id.size);
            name = itemView.findViewById(R.id.food_name);
            resImage = itemView.findViewById(R.id.image_res);
            add = itemView.findViewById(R.id.add_image);
            remove = itemView.findViewById(R.id.remove_quantity);
            quan = itemView.findViewById(R.id.his_quantity);
        }
    }

    private void openAlert(final int id, final int position, final int quaniss, final int resId, final String type) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.or_der);
        alertDialog.setMessage(R.string.delete_cart);
        alertDialog.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                addFood(id, position, quaniss, resId, type);
            }
        });
        alertDialog.setNegativeButton(R.string.delete_all, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletAll(id, position, quaniss, resId, type);
                addFood(id, position, quaniss, resId, type);
            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public void deletAll(final int id, final int position, final int quaniss, final int resId, final String type) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            RequestBody body = null ;
            try {
                body = MainApiBody.deleteAllOrder(type,resId);
            }catch (Exception e){

            }

            MainApi.deleteOrder(StaticMethods.userData.getApi_token(), body, new ConnectionListener<DataObj<ChangePass>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<ChangePass>> connectionResponse) {
                    if (connectionResponse.data.getData().isStatus()) {
                        ToastUtil.showSuccessToast(context, R.string.orderDeleted);
                        //addFood(id, position, quaniss, resId, type);
                    } else {
                        ToastUtil.showSuccessToast(context, connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    ToastUtil.showSuccessToast(context, R.string.error);
                }
            });
        } else {
            ToastUtil.showSuccessToast(context, R.string.noConnection);
        }
    }

}
