package com.aaamab.bonappetit.ui.adapter.viewPagerAdapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.ui.curbsideHome.CurbsideHome;
import com.aaamab.bonappetit.ui.dinein.DineInScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.ui.pickupHome.PickupHome;

public class HomePagerAdapter extends FragmentPagerAdapter {
    Context context;

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new DineInScreen();
        }
        else if (position == 1)
        {
            fragment = new CurbsideHome();
        }
        else if (position == 2)
        {
            fragment = new PickupHome();
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
            title = context.getString(R.string.dine_in);
        }
        else if (position == 1)
        {
            title = context.getString(R.string.cur_page);
        }
        else if (position == 2)
        {
            title = context.getString(R.string.pic_pager) ;
        }
        return title;
    }
}
