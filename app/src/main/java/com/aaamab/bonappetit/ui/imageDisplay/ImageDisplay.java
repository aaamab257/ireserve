package com.aaamab.bonappetit.ui.imageDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivityImageDisplayBinding;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.squareup.picasso.Picasso;

public class ImageDisplay extends AppCompatActivity {
    ActivityImageDisplayBinding binding ;
    ImageHandler handler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_image_display);
        handler = new ImageHandler(this);
        binding.setHandler(handler);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("img");
        Picasso.with(this).load(url).into(binding.img);
    }

    public class ImageHandler{
        Context context ;

        public ImageHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view){
            onBackPressed();
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}