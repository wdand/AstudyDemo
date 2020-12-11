package com.example.studydemo.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bingkong.bkbase.LoginApi;
import com.bingkong.bkbase.YFWLoginInfoRes;
import com.bingkong.bkbase.base.BaseFluxFragment;
import com.bingkong.bkbase.flux.stores.Store;
import com.bumptech.glide.Glide;
import com.example.studydemo.AllDemoActivity;
import com.example.studydemo.CombApi;
import com.example.studydemo.MainActivity;
import com.example.studydemo.R;
import com.example.studydemo.ReqDemo;
import com.example.studydemo.RetrofitRxJavaDemo;
import com.example.studydemo.StoreDemo;
import com.example.studydemo.banner.adapter.ImageAdapter;
import com.example.studydemo.banner.adapter.ImageNetAdapter;
import com.example.studydemo.banner.adapter.ImageTitleNumAdapter;
import com.example.studydemo.banner.bean.BannerDataBean;
import com.example.studydemo.bean.HomeAllDataInfoBean;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.RotateYTransformer;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.LogUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.home_banner_bg)
    ImageView homeBannerBg;
    @BindView(R.id.hoem_media)
    ImageView hoem_media;


    @Override
    public void initBus() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        actionsCreator().getHomeData(this, "wx", "N");
//        ImageAdapter adapter = new ImageAdapter(BannerDataBean.getTestData2());
//        banner.setAdapter(adapter)//设置适配器
//                .addBannerLifecycleObserver(this)//添加生命周期观察者
//                .setBannerRound(BannerUtils.dp2px(5))//圆角
//                .addPageTransformer(new RotateYTransformer())//添加切换效果
//                .setIndicator(new CircleIndicator(this.getActivity()))//设置指示器
//                .setOnBannerListener((data, position) -> {
//                    Snackbar.make(banner, ((BannerDataBean) data).title, Snackbar.LENGTH_SHORT).show();
//                    LogUtils.d("position：" + position);
//                });//设置点击事件,传this也行

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

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);

        if (CombApi.APITAG_GETHOEMDATA.equals(event.url)) {
            if (event.code == 200) {
                HomeAllDataInfoBean.ResultBean resultBean = (HomeAllDataInfoBean.ResultBean) event.data;
                List<BannerDataBean> mDatas = new ArrayList<>();
                for (int i = 0; i < resultBean.getData_items().getBanner().getItems().size(); i++) {
                    HomeAllDataInfoBean.ResultBean.DataItemsBean.BannerBean.ItemsBeanXXXXXX itemsBean = resultBean.getData_items().getBanner().getItems().get(i);
                    BannerDataBean bannerDataBean = new BannerDataBean(itemsBean.getImg_url(), itemsBean.getName(), 1);
                    mDatas.add(bannerDataBean);
                }
                Glide.with(HomeFragment.this).load("https://c1.yaofangwang.net/19/3541/9e90569121c067bde29b5a50e641dedb.nwm.jpg").into(homeBannerBg);
                Glide.with(HomeFragment.this).load("https://c1.yaofangwang.net/19/2878/7f5ab224a4cbaf0fa0da52ef457761b0.nwm.png").into(hoem_media);
                refresh.setEnabled(true);
                banner.setAdapter(new ImageNetAdapter(mDatas));
                banner.removeIndicator();
            }
        }
    }
}