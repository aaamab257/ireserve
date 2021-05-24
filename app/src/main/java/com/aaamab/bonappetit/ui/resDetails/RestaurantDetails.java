package com.aaamab.bonappetit.ui.resDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MakeFavorites;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.databinding.ActivityRestaurantDetailsBinding;
import com.aaamab.bonappetit.ui.adapter.ImagesAdapter;
import com.aaamab.bonappetit.ui.adapter.viewPagerAdapters.HomePagerAdapter;
import com.aaamab.bonappetit.ui.adapter.viewPagerAdapters.MenuAndReviewPagerAdapter;
import com.aaamab.bonappetit.ui.cubside.CurbsideScreen;
import com.aaamab.bonappetit.ui.dineinOrder.DineInOrderScreen;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.myOrder.MyOrderScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.squareup.picasso.Picasso;

public class RestaurantDetails extends AppCompatActivity implements ResByIdInter {

    ActivityRestaurantDetailsBinding binding ;
    ResHandler handler ;
    MenuAndReviewPagerAdapter andReviewPagerAdapter ;
    CustomDialog dialog ;
    int index =0;
    ResPresenter presenter;
    boolean isFav = false ;
    ImagesAdapter adapter ;
    String name , image ;
    Handler handlers;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_restaurant_details);
        handler = new ResHandler(this);
        binding.setHandler(handler);
        dialog = new CustomDialog(this);
        presenter = new ResPresenter(this);

        Bundle b = getIntent().getExtras();
        if (StaticMethods.type.equals("D")){
            binding.btnC.setText(getString(R.string.reserve));
        }else if (StaticMethods.type.equals("P")){
            binding.btnC.setText(getString(R.string.apply));
        }else  if (StaticMethods.type.equals("C")) {
            binding.btnC.setText(getString(R.string.apply));
        }else {
            binding.btnC.setVisibility(View.GONE);
        }
        try {
            index = b.getInt("id");
        }catch (Exception e){

        }

        //dialog.showDialog();
        presenter.getDetails(this ,index);
        configTabs();
    }
    private void configTabs() {
        andReviewPagerAdapter = new MenuAndReviewPagerAdapter(getSupportFragmentManager(), this);
        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.pager.setAdapter(andReviewPagerAdapter);
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0){
                    binding.btuns.setVisibility(View.VISIBLE);
                }else if (position == 1){
                    binding.btuns.setVisibility(View.GONE);
                }else {
                    binding.btuns.setVisibility(View.GONE);
                }
            }

            public void onPageSelected(int position) {
                if (position == 0){
                    binding.btuns.setVisibility(View.VISIBLE);
                }else if (position == 1){
                    binding.btuns.setVisibility(View.GONE);
                }else {
                    binding.btuns.setVisibility(View.GONE);
                }
            }
        });
    }
    public void goneButtons(boolean isGone){

    }
    @Override
    public void onSuccess(RestruantByID restruant) {
        dialog.dismissDialog();
        adapter = new ImagesAdapter(RestaurantDetails.this , restruant.getRes().get(0).getImages());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(RestaurantDetails.this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(5);

        binding.imageView5.setLayoutManager(layoutManager);
        binding.imageView5.setAdapter(adapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.imageView5);
        putDelay(binding.imageView5 ,adapter);
        name = restruant.getRes().get(0).getName();
        image = restruant.getRes().get(0).getMain_image();
        binding.textView6.setText(restruant.getRes().get(0).getName());
        if(restruant.getRes().get(0).getTypesItems().size() > 0){
            binding.textView8.setText(restruant.getRes().get(0).getTypesItems().get(0).getName());
        }
        //binding.textView9.setText(restruant.getRes().get(0).getDescription_en());
        if(restruant.getRes().get(0).getIs_favorit() == 0){
            binding.imageView7.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            isFav = false ;
        }else {
            binding.imageView7.setImageResource(R.drawable.heart_active);
            isFav = true ;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (handlers != null) {
            handlers.removeCallbacks(runnable);
        }
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(this,error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(this,R.string.noConnection);
    }

    @Override
    public void onMakeFav(DataObj<MakeFavorites> dataObj) {
        dialog.dismissDialog();
        binding.imageView7.setImageResource(R.drawable.heart_active);
        isFav = true ;
    }

    @Override
    public void onRemoveFav(DataObj<MakeFavorites> dataObj) {
        dialog.dismissDialog();
        binding.imageView7.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        isFav = false ;
    }

    public class ResHandler{
        Context context;

        public ResHandler(Context context) {
            this.context = context;
        }
        public void onBack(View v){

            onBackPressed();

        }

        public void onFav(View v){
            if(isFav){
                //dialog.showDialog();
                presenter.removeFav(RestaurantDetails.this , index);
            }else {
                //dialog.showDialog();
                presenter.makeFav(RestaurantDetails.this , index);
            }

        }

        public void onButtonClick(View v){
            if (StaticMethods.type.equals("D")){
                Bundle bundle = new Bundle();
                bundle.putString("name" ,name);
                bundle.putString("image" , image);
                IntentUtilies.openActivityWithBundle(RestaurantDetails.this, DineInOrderScreen.class , bundle);
            }else if (StaticMethods.type.equals("P")){
                Bundle b = new Bundle();
                b.putString("type", "P");
                b.putInt("resId" , StaticMethods.resID);
                IntentUtilies.openActivityWithBundle(RestaurantDetails.this, MyOrderScreen.class, b);
            }else  if (StaticMethods.type.equals("C")) {
                Bundle b = new Bundle();
                b.putString("type", "C");
                b.putInt("resId" , StaticMethods.resID);
                IntentUtilies.openActivityWithBundle(RestaurantDetails.this, MyOrderScreen.class, b);
            }
        }

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }


    public void putDelay(final RecyclerView recyclerView, final ImagesAdapter adapter) {
        final int speedScroll = 3000;

        handlers = new Handler();
        runnable = new Runnable() {
            int count = 0;
            boolean flag = true;

            @Override
            public void run() {
                if (count < adapter.getItemCount()) {
                    if (count == adapter.getItemCount() - 1) {
                        flag = false;
                    } else if (count == 0) {
                        flag = true;
                    }
                    if (flag) count++;
                    else count--;

                    recyclerView.smoothScrollToPosition(count);

                    handlers.postDelayed(this, speedScroll);
                }
            }
        };

        handlers.postDelayed(runnable, speedScroll);
    }
    public void openPickUp(){
        Bundle bundle = new Bundle();

        bundle.putInt("type" , 1);
        IntentUtilies.openActivityWithBundle(RestaurantDetails.this, PickupScreen.class,bundle);
    }
    public void openCurbside(){
        Bundle bundle = new Bundle();

        bundle.putInt("type" , 1);
        IntentUtilies.openActivityWithBundle(RestaurantDetails.this, CurbsideScreen.class , bundle);
    }
    public void openDineIn(){
        Bundle bundle = new Bundle();
        bundle.putString("name" ,name);
        bundle.putString("image" , image);
        IntentUtilies.openActivityWithBundle(RestaurantDetails.this, DineInOrderScreen.class , bundle);
    }
}