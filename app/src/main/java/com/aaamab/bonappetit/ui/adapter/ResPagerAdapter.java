package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.ui.curbsideHome.CurbsideHome;
import com.aaamab.bonappetit.ui.dinein.DineInScreen;
import com.aaamab.bonappetit.ui.pickupHome.PickupHome;
import com.aaamab.bonappetit.ui.resCurbside.ResCurbsideScreen;
import com.aaamab.bonappetit.ui.resDine.ResDineinScreen;
import com.aaamab.bonappetit.ui.resPickup.ResPickupScreen;

public class ResPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public ResPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ResDineinScreen();
        }
        else if (position == 1)
        {
            fragment = new ResCurbsideScreen();
        }
        else if (position == 2)
        {
            fragment = new ResPickupScreen();
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
