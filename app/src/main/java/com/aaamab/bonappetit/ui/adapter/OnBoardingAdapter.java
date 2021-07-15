package com.aaamab.bonappetit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.OnBoardItem;

import java.util.ArrayList;

public class OnBoardingAdapter extends PagerAdapter {

    Context context;
    ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();

    public OnBoardingAdapter(Context context, ArrayList<OnBoardItem> onBoardItems) {
        this.context = context;
        this.onBoardItems = onBoardItems;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.onboard_item, container, false);

        OnBoardItem item = onBoardItems.get(position);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_onboard);
        imageView.setImageResource(item.getImageId());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
