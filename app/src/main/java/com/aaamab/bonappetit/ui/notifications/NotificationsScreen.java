package com.aaamab.bonappetit.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.NotificationArray;
import com.aaamab.bonappetit.databinding.ActivityNotificationsScreenBinding;
import com.aaamab.bonappetit.ui.adapter.NotificationsAdapter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class NotificationsScreen extends AppCompatActivity implements NotificationInter{

    ActivityNotificationsScreenBinding binding ;
    NotificationsHandler handler ;
    NotificationsAdapter adapter ;
    NotificationPresenter presenter ;
    CustomDialog dialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications_screen);
        handler = new NotificationsHandler(this);
        binding.setHandler(handler);
        presenter = new NotificationPresenter(this);
        dialog = new CustomDialog(this);
        //dialog.showDialog();
        presenter.getNotifi(this);

    }

    @Override
    public void onNotification(NotificationArray array) {
        if (array.notificationItems.size() == 0 ){
            binding.txtNoData.setVisibility(View.VISIBLE);
        }else {
            adapter = new NotificationsAdapter(this , array);
            binding.recNotifications.setLayoutManager(new LinearLayoutManager(this));
            binding.recNotifications.setAdapter(adapter);
        }


    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(NotificationsScreen.this , R.string.no_data);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showInfoToast(NotificationsScreen.this , R.string.noConnection);
    }

    public class NotificationsHandler{
        Context context ;

        public NotificationsHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view){
            onBackPressed();
        }
    }
}