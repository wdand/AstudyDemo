package com.example.studydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bingkong.bkbase.base.BaseFluxFragment;
import com.example.studydemo.banner.adapter.ImageTitleNumAdapter;
import com.example.studydemo.banner.bean.BannerDataBean;
import com.example.studydemo.bottomnavigations.BlankFragment;
import com.example.studydemo.bottomnavigations.HelloWorld;
import com.example.studydemo.findyao.FindYaoFragment;
import com.example.studydemo.home.HomeFragment;
import com.example.studydemo.manager.ActivityStackManager;
import com.example.studydemo.mine.MineFragment;
import com.example.studydemo.shopcar.ShopCarFragment;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ArouterConstants.MAIN_ACT)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static boolean isExit = false;
    private LinearLayout home;
    private LinearLayout fineYao;
    private LinearLayout shopCar;
    private LinearLayout mine;
    private HomeFragment homeFragment;
    private FindYaoFragment findYaoFragment;
    private ShopCarFragment shopCarFragment;
    private MineFragment mineFragment;
    private Fragment mContent;
    private FrameLayout layout;
    private ImageView homeImg;
    private ImageView shopcarImg;
    private ImageView findyaoImg;
    private ImageView mineImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton_and_fragment);
        ButterKnife.bind(this);
        homeImg = findViewById(R.id.bottom_home_img);
        findyaoImg = findViewById(R.id.bottom_findyao_img);
        shopcarImg = findViewById(R.id.bottom_shopcar_img);
        mineImg = findViewById(R.id.bottom_mine_img);
        home = findViewById(R.id.main_home);
        shopCar = findViewById(R.id.main_shopcar);
        fineYao = findViewById(R.id.main_findyao);
        mine = findViewById(R.id.main_mine);
        layout = findViewById(R.id.fragment_container);
        switchContent(home);
        homeImg.setSelected(true);
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        home.setOnClickListener(this);
        shopCar.setOnClickListener(this);
        fineYao.setOnClickListener(this);
        mine.setOnClickListener(this);
        findViewById(R.id.sign_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, AllDemoActivity.class));
            }
        });
    }

    private void selectClear(){
        homeImg.setSelected(false);
        findyaoImg.setSelected(false);
        shopcarImg.setSelected(false);
        mineImg.setSelected(false);
    }

    public void switchContent(View view) {
        Fragment fragment;
        if (view == home) {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
            }
            fragment = homeFragment;
            selectClear();
            homeImg.setSelected(true);
        } else if (view == fineYao) {
            if (findYaoFragment == null) {
                findYaoFragment = new FindYaoFragment();
            }
            fragment = findYaoFragment;
            selectClear();
            findyaoImg.setSelected(true);
        } else if (view == shopCar) {
            if (shopCarFragment == null) {
                shopCarFragment = new ShopCarFragment();
            }
            fragment = shopCarFragment;
            selectClear();
            shopcarImg.setSelected(true);
        } else if (view == mine) {
            if (mineFragment == null) {
                mineFragment = new MineFragment();
            }
            fragment = mineFragment;
            selectClear();
            mineImg.setSelected(true);
        } else {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mContent == null) {
            transaction.add(layout.getId(), fragment).commit();
            mContent = fragment;
        }
        if (mContent != fragment) {
            if (!fragment.isAdded()) {
                transaction.hide(mContent).add(layout.getId(), fragment).commitAllowingStateLoss();
            } else {
                transaction.hide(mContent).show(fragment).commitAllowingStateLoss();
            }
            mContent = fragment;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i= new Intent(Intent.ACTION_MAIN);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
//            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    Handler mHandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息，2000==2秒
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            ActivityStackManager.getManager().exitApp(this);
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_home:
                switchContent(home);
                break;
            case R.id.main_findyao:
                switchContent(fineYao);
                break;
            case R.id.main_shopcar:
                switchContent(shopCar);
                break;
            case R.id.main_mine:
                switchContent(mine);
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        //实现Home键效果
//        //super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了
//        Intent i= new Intent(Intent.ACTION_MAIN);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addCategory(Intent.CATEGORY_HOME);
//        startActivity(i);
//    }
}
