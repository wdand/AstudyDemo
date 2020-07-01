package com.example.studydemo.banner.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studydemo.R;
import com.example.studydemo.banner.adapter.ImageNetAdapter;
import com.example.studydemo.banner.bean.BannerDataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstraintLayoutBannerActivity extends AppCompatActivity {
    private static final String TAG = "banner_log";
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_banner);
        ButterKnife.bind(this);
        banner.setAdapter(new ImageNetAdapter(BannerDataBean.getTestData3()));
        banner.addBannerLifecycleObserver(this);
        banner.setIndicator(new CircleIndicator(this));
    }

}
