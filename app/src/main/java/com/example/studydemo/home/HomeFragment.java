package com.example.studydemo.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bingkong.bkbase.base.BaseFluxFragment;
import com.example.studydemo.AllDemoActivity;
import com.example.studydemo.MainActivity;
import com.example.studydemo.R;
import com.example.studydemo.ReqDemo;
import com.example.studydemo.RetrofitRxJavaDemo;
import com.example.studydemo.StoreDemo;
import com.example.studydemo.banner.adapter.ImageAdapter;
import com.example.studydemo.banner.adapter.ImageNetAdapter;
import com.example.studydemo.banner.adapter.ImageTitleNumAdapter;
import com.example.studydemo.banner.bean.BannerDataBean;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.LogUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFluxFragment<StoreDemo, ReqDemo> {
    @BindView(R.id.homeClick)
    TextView add_combination;
    @BindView(R.id.homeSwipeRefresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.homeBanner)
    Banner banner;
    @Override
    public void initBus() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ImageAdapter adapter = new ImageAdapter(BannerDataBean.getTestData2());

        banner.setAdapter(adapter)//设置适配器
//              .setCurrentItem(3,false)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
//              .setBannerRound(BannerUtils.dp2px(5))//圆角
//              .addPageTransformer(new RotateYTransformer())//添加切换效果
                .setIndicator(new CircleIndicator(this.getActivity()))//设置指示器
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(banner, ((BannerDataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });//设置点击事件,传this也行
        refresh.setEnabled(true);
        banner.setAdapter(new ImageNetAdapter(BannerDataBean.getYFWBannerData()));
        banner.removeIndicator();
        add_combination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.mContext, RetrofitRxJavaDemo.class));

            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}