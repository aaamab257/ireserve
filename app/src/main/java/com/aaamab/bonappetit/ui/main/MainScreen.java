package com.aaamab.bonappetit.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.databinding.ActivityMainScreenBinding;
import com.aaamab.bonappetit.helpers.prefs.PrefUtils;
import com.aaamab.bonappetit.ui.about.AboutScreen;
import com.aaamab.bonappetit.ui.boooking.BookingScreen;
import com.aaamab.bonappetit.ui.contactUs.ContactUsScreen;
import com.aaamab.bonappetit.ui.cubside.CurbsideScreen;
import com.aaamab.bonappetit.ui.dinein.DineInHomeInter;
import com.aaamab.bonappetit.ui.dinein.DineInHomePresenter;
import com.aaamab.bonappetit.ui.dineinOrder.DineInOrderScreen;
import com.aaamab.bonappetit.ui.favorites.FavoriteScreen;
import com.aaamab.bonappetit.ui.filter.FilterScreen;
import com.aaamab.bonappetit.ui.home.HomeScreen;
import com.aaamab.bonappetit.ui.login.LoginScreen;
import com.aaamab.bonappetit.ui.more.MoreScreen;
import com.aaamab.bonappetit.ui.myOrders.MyOrdersScreen;
import com.aaamab.bonappetit.ui.notifications.NotificationsScreen;
import com.aaamab.bonappetit.ui.offers.OffersScreen;
import com.aaamab.bonappetit.ui.paymentMore.PaymentDetailsScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.ui.profile.Profile;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.ui.reservations.ReservationsScreen;
import com.aaamab.bonappetit.ui.searchResult.SearchResult;
import com.aaamab.bonappetit.ui.splash.SplashScreen;
import com.aaamab.bonappetit.ui.support.SupportScreen;
import com.aaamab.bonappetit.ui.termsAndConditions.ConditionsScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;

public class MainScreen extends AppCompatActivity implements DineInHomeInter {
    ActivityMainScreenBinding binding;
    MainHandler handler;
    int position = 0;
    FragmentManager fragmentManager;
    DineInHomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        handler = new MainHandler(this);
        binding.setHandler(handler);
        presenter = new DineInHomePresenter(this);
        presenter.isRated(this);
        openView(0);
    }

    private void openView(int position) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                Log.e("TAG", "openView: " + "Here1");
                updateUI(position);
                Log.e("TAG", "openView: " + "Here2");
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.fragment_container, new HomeScreen());
                Log.e("TAG", "openView: " + "Here3");
                break;
            case 1:
                updateUI(position);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.fragment_container, new ReservationsScreen());
                break;
            case 2:
                updateUI(position);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.fragment_container, new OffersScreen());
                break;
            case 3:
                updateUI(position);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.fragment_container, new FavoriteScreen());
                break;
            case 4:
                updateUI(position);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.fragment_container, new MoreScreen());
                break;
        }
        fragmentTransaction.commit();
    }

    private void updateUI(int position) {
        switch (position) {
            case 0:
                binding.iconFav.setImageResource(R.drawable.noun_heart);
                binding.iconHome.setImageResource(R.drawable.new_home_active);
                binding.iconMore.setImageResource(R.drawable.noun_more);
                binding.iconOffers.setImageResource(R.drawable.noun_offer);
                binding.iconReser.setImageResource(R.drawable.noun_reservation);

                binding.txtHome.setTextColor(getResources().getColor(R.color.colorActive));
                binding.txtReservations.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtFavorites.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtMore.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtOffers.setTextColor(getResources().getColor(R.color.colorNoun));
                break;
            case 1:
                binding.iconFav.setImageResource(R.drawable.noun_heart);
                binding.iconHome.setImageResource(R.drawable.new_home_unactive);
                binding.iconMore.setImageResource(R.drawable.noun_more);
                binding.iconOffers.setImageResource(R.drawable.noun_offer);
                binding.iconReser.setImageResource(R.drawable.active_reservation);

                binding.txtHome.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtReservations.setTextColor(getResources().getColor(R.color.colorActive));
                binding.txtFavorites.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtMore.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtOffers.setTextColor(getResources().getColor(R.color.colorNoun));
                break;
            case 2:
                binding.iconFav.setImageResource(R.drawable.noun_heart);
                binding.iconHome.setImageResource(R.drawable.new_home_unactive);
                binding.iconMore.setImageResource(R.drawable.noun_more);
                binding.iconOffers.setImageResource(R.drawable.active_offers);
                binding.iconReser.setImageResource(R.drawable.noun_reservation);

                binding.txtHome.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtReservations.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtFavorites.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtMore.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtOffers.setTextColor(getResources().getColor(R.color.colorActive));
                break;
            case 3:
                binding.iconFav.setImageResource(R.drawable.active_heart);
                binding.iconHome.setImageResource(R.drawable.new_home_unactive);
                binding.iconMore.setImageResource(R.drawable.noun_more);
                binding.iconOffers.setImageResource(R.drawable.noun_offer);
                binding.iconReser.setImageResource(R.drawable.noun_reservation);

                binding.txtHome.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtReservations.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtFavorites.setTextColor(getResources().getColor(R.color.colorActive));
                binding.txtMore.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtOffers.setTextColor(getResources().getColor(R.color.colorNoun));
                break;
            case 4:
                binding.iconFav.setImageResource(R.drawable.noun_heart);
                binding.iconHome.setImageResource(R.drawable.new_home_unactive);
                binding.iconMore.setImageResource(R.drawable.active_more);
                binding.iconOffers.setImageResource(R.drawable.noun_offer);
                binding.iconReser.setImageResource(R.drawable.noun_reservation);

                binding.txtHome.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtReservations.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtFavorites.setTextColor(getResources().getColor(R.color.colorNoun));
                binding.txtMore.setTextColor(getResources().getColor(R.color.colorActive));
                binding.txtOffers.setTextColor(getResources().getColor(R.color.colorNoun));
                break;
        }
    }

    public void goToResDetails(int id) {
        Bundle b = new Bundle();
        b.putInt("id", id);
        IntentUtilies.openActivityWithBundle(MainScreen.this, RestaurantDetails.class, b);
    }

    public void profile() {
        IntentUtilies.openActivity(MainScreen.this, Profile.class);
    } //openResultScreen

    public void openResultScreen() {
        IntentUtilies.openActivity(MainScreen.this, SearchResult.class);
    }

    public void filter() {
        IntentUtilies.openActivity(MainScreen.this, FilterScreen.class);
    }

    public void onCance() {
        openView(position);
    }

    public void about() {
        IntentUtilies.openActivity(MainScreen.this, AboutScreen.class);
    }

    public void contactUs() {
        IntentUtilies.openActivity(MainScreen.this, ContactUsScreen.class);
    }

    public void notifications() {
        IntentUtilies.openActivity(MainScreen.this, NotificationsScreen.class);
    }

    public void terms() {
        IntentUtilies.openActivity(MainScreen.this, ConditionsScreen.class);
    }

    public void support() {
        IntentUtilies.openActivity(MainScreen.this, SupportScreen.class);
    }

    public void bookings() {
        IntentUtilies.openActivity(MainScreen.this, BookingScreen.class);
    }

    public void onSignOut() {
        StaticMethods.ClearChash();
        PrefUtils.SignOut_User(MainScreen.this);
        IntentUtilies.openActivityInNewStack(MainScreen.this, LoginScreen.class);
    }

    public void onMyOrders() {
        IntentUtilies.openActivity(MainScreen.this, MyOrdersScreen.class);
    }
    public void myPay(){
        IntentUtilies.openActivity(MainScreen.this, PaymentDetailsScreen.class);
    }

    @Override
    public void onSuccess(RestArray array) {

    }

    @Override
    public void onFail(String error) {

    }

    @Override
    public void onConnection(boolean isConnected) {

    }

    @Override
    public void onUpComing(Upcomming array) {

    }

    @Override
    public void isRated(boolean isRated) {
        if (isRated){
            openResetPassDialog();
        }
    }

    public class MainHandler {
        Context context;

        public MainHandler(Context context) {
            this.context = context;
        }

        public void home(View v) {
            position = 0;
            openView(0);
        }

        public void reservations(View v) {
            position = 1;
            openView(position);
        }

        public void offers(View v) {
            position = 2;
            openView(position);
        }

        public void favorites(View v) {
            position = 3;
            openView(position);
        }

        public void more(View v) {
            position = 4;
            openView(position);
        }
    }

    public void openPickUp() {
        IntentUtilies.openActivity(MainScreen.this, PickupScreen.class);
    }

    public void openCurbside() {
        IntentUtilies.openActivity(MainScreen.this, CurbsideScreen.class);
    }

    public void openDineIn(Bundle bundle) {
        IntentUtilies.openActivityWithBundle(MainScreen.this, DineInOrderScreen.class, bundle);
    }

    @Override
    public void onBackPressed() {

        AlertDialog alertDialog = new AlertDialog.Builder(MainScreen.this).create();
        alertDialog.setTitle(getString(R.string.alert));
        alertDialog.setMessage(getString(R.string.close_app));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, MainScreen.this.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void openResetPassDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_rate, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button apply = dialogView.findViewById(R.id.btn_apply);
        Button cancel = dialogView.findViewById(R.id.btn_cancel);
        RatingBar rate = dialogView.findViewById(R.id.ratingBar);
        final float rating = rate.getRating();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rating == 0){
                    presenter.rate(MainScreen.this , rating);
                    alertDialog.dismiss();
                }else {
                    ToastUtil.showErrorToast(MainScreen.this, R.string.your_rate);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cancel(MainScreen.this);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }


    // social media methods
    public void social(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }

    public void openSplash(){
        IntentUtilies.openActivityInNewStack(this, SplashScreen.class);

    }
}