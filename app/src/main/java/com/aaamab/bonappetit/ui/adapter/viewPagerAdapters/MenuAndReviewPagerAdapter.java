package com.aaamab.bonappetit.ui.adapter.viewPagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.ui.dinein.DineInScreen;
import com.aaamab.bonappetit.ui.menus.MenuScreen;
import com.aaamab.bonappetit.ui.resAbout.ResAboutScreen;
import com.aaamab.bonappetit.ui.review.ReviewScreen;

public class MenuAndReviewPagerAdapter extends FragmentPagerAdapter {
    Context context ;
    public MenuAndReviewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new MenuScreen();
        }
        else if (position == 1)
        {
            fragment = new ReviewScreen();
        }else if (position == 2)
        {
            fragment = new ResAboutScreen();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = context.getString(R.string.menu_pager);
        }
        else if (position == 1)
        {
            title = context.getString(R.string.reviews_pager);
        }else if (position == 2)
        {
            title = context.getString(R.string.about_pager);
        }

        return title;
    }
}
