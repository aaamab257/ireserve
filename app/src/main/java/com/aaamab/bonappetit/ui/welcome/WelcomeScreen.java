package com.aaamab.bonappetit.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.OnBoardItem;
import com.aaamab.bonappetit.databinding.ActivityWelcomeScreenBinding;
import com.aaamab.bonappetit.ui.adapter.OnBoardingAdapter;
import com.aaamab.bonappetit.ui.login.LoginScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;

import java.util.ArrayList;

public class WelcomeScreen extends AppCompatActivity {

    ActivityWelcomeScreenBinding binding;
    WelcomeHandler welcomeHandler;

    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;


    private ViewPager onboard_pager;

    private OnBoardingAdapter mAdapter;
    int previous_pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome_screen);
        welcomeHandler = new WelcomeHandler(this);
        binding.setHandler(welcomeHandler);

        mAdapter = new OnBoardingAdapter(this, loadData());
        binding.pagerIntroduction.setAdapter(mAdapter);
        binding.pagerIntroduction.setCurrentItem(0);
        binding.pagerIntroduction.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(WelcomeScreen.this, R.drawable.non_selected_item_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(WelcomeScreen.this, R.drawable.selected_item_dot));


                int pos = position + 1;

                if (pos == dotsCount && previous_pos == (dotsCount - 1))
                    show_animation();
                else if (pos == (dotsCount - 1) && previous_pos == dotsCount)
                    hide_animation();

                previous_pos = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setUiPageViewController();
    }

    public class WelcomeHandler {
        Context context;

        public WelcomeHandler(Context context) {
            this.context = context;
        }

        public void onStart(View v) {

            IntentUtilies.openActivityInNewStack(WelcomeScreen.this , LoginScreen.class);
        }
    }

    public ArrayList<OnBoardItem> loadData() {

        ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();
        int[] imageId = {R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.fourth, R.drawable.fivth};

        for (int i = 0; i < imageId.length; i++) {
            OnBoardItem item = new OnBoardItem();
            item.setImageId(imageId[i]);


            onBoardItems.add(item);
        }

        return onBoardItems;
    }


    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(WelcomeScreen.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);

            binding.viewPagerCountDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(WelcomeScreen.this, R.drawable.selected_item_dot));
    }

    public void hide_animation() {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);

        binding.btnGetStarted.startAnimation(hide);

        hide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.btnGetStarted.clearAnimation();
                binding.btnGetStarted.setVisibility(View.GONE);

            }

        });


    }

    public void show_animation() {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        binding.btnGetStarted.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                binding.btnGetStarted.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.btnGetStarted.clearAnimation();

            }

        });


    }
}