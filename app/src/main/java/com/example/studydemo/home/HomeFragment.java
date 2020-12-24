package com.example.studydemo.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingkong.bkbase.base.BaseFluxFragment;
import com.bingkong.bkbase.flux.stores.Store;
import com.bumptech.glide.Glide;
import com.example.studydemo.CombApi;
import com.example.studydemo.R;
import com.example.studydemo.ReqDemo;
import com.example.studydemo.RetrofitRxJavaDemo;
import com.example.studydemo.StoreDemo;
import com.example.studydemo.banner.adapter.ImageNetAdapter;
import com.example.studydemo.banner.bean.BannerDataBean;
import com.example.studydemo.bean.HomeAllDataInfoBean;
import com.example.studydemo.customwidget.customdialog.CommonDialog;
import com.example.studydemo.selectcolorview.ColorPlatte;
import com.example.studydemo.selectcolorview.ProductColorData;
import com.example.studydemo.selectcolorview.SelectColorPanel;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFluxFragment<StoreDemo, ReqDemo> implements EasyPermissions.PermissionCallbacks {
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
    @BindView(R.id.clickOrTuchDemo)
    TextView clickOrTuchDemo;
//    @BindView(R.id.canmovebutton)
//    Button canmovebutton;
    @BindView(R.id.HomefmttitleTextView)
    TextView titleTextView;
    @BindView(R.id.product_coler)
    SelectColorPanel mColorPanel;
    private int position = 0;
    private String string;
    private ArrayList<ProductColorData> mDataList = new ArrayList<>();

    @Override
    public void initBus() {
        Log.e(getClass().getSimpleName(), "initBus: ");
    }

    public void getAuthorization() {
        String[] PERMISSIONS_STORAGE = {
                android.Manifest.permission.READ_SMS,
                android.Manifest.permission.READ_PHONE_NUMBERS,
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.RECORD_AUDIO
        };
        EasyPermissions.requestPermissions(this.getActivity(), "需要获取手机权限", 10000, PERMISSIONS_STORAGE);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        actionsCreator().getHomeData(this, "wx", "N");
        CommonDialog commonDialog = new CommonDialog(this.getContext());
        for (int i = 0; i < ColorPlatte.COLOR_PLATTES.length; i++) {
            ProductColorData materialData = new ProductColorData();
            materialData.setType(1);
            materialData.setColor(ColorPlatte.COLOR_PLATTES[i]);
            materialData.setColorName(i+"");
            mDataList.add(materialData);
        }
        mColorPanel.setDataList( mDataList );
        mColorPanel.setChangeListener(new SelectColorPanel.ChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(ProductColorData materialData) {
                titleTextView.setTextColor(Color.parseColor(materialData.getColor()));
            }
        });

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
        string = titleTextView.getText().toString();
        handler.sendEmptyMessage(0x158);

        clickOrTuchDemo.setOnTouchListener(new View.OnTouchListener() {
            float beforeMoveY;
            float afterMoveY;

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("test", "DOWN");
                    beforeMoveY = motionEvent.getX();
                    return false; //DOWN时返回true
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    afterMoveY = motionEvent.getX();
                    if (beforeMoveY > afterMoveY) {
                        Log.d("test", "左滑");
                    } else {
                        Log.d("test", "右滑");

                    }
                    Log.d("test", "MOVE");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("test", "UP");
                }
                return false;
            }
        });
        clickOrTuchDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "click");
                Toasty.normal(mContext, "关闭通知测试是否能弹出").show();
            }
        });
        add_combination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.mContext, RetrofitRxJavaDemo.class));

            }
        });
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAuthorization();
                TelephonyManager tm = (TelephonyManager) HomeFragment.this.getContext().getSystemService(Context.TELEPHONY_SERVICE);
                String deviceid = tm.getDeviceId();
                if (ActivityCompat.checkSelfPermission(HomeFragment.this.getContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(HomeFragment.this.getContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(HomeFragment.this.getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                String tel = tm.getLine1Number();//手机号码
                String imei = tm.getSimSerialNumber();
                String imsi = tm.getSubscriberId();
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
                Glide.with(HomeFragment.this.getContext()).load("https://c1.yaofangwang.net/19/3541/9e90569121c067bde29b5a50e641dedb.nwm.jpg").into(homeBannerBg);
                Glide.with(HomeFragment.this.getContext()).load("https://c1.yaofangwang.net/19/2878/7f5ab224a4cbaf0fa0da52ef457761b0.nwm.png").into(hoem_media);
                refresh.setEnabled(true);
                banner.setAdapter(new ImageNetAdapter(mDatas));
                banner.removeIndicator();
            }
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x158:
                    SpannableString spannableString = new SpannableString(string);
                    RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.4f);
                    spannableString.setSpan(sizeSpan, position, position + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    titleTextView.setText(spannableString);
                    position++;
                    if (position >= titleTextView.getText().toString().length()) {
                        position = 0;
                    }
                    handler.sendEmptyMessageDelayed(0x158, 150);
                    break;
            }
        }
    };



    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.e("onPermissionsGranted", "onPermissionsGranted: " );
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.e("onPermissionsGranted", "onPermissionsDenied: " );

    }
}