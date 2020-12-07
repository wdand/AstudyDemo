package com.example.studydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.studydemo.bottomnavigations.BlankFragment;
import com.example.studydemo.bottomnavigations.HelloWorld;
import com.example.studydemo.home.HomeFragment;
import com.example.studydemo.manager.ActivityStackManager;
import com.example.studydemo.shopcar.ShopCarFragment;

@Route(path = ArouterConstants.MAIN_ACT)
public class MainActivity extends AppCompatActivity {
    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    private static boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton_and_fragment);
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }

    private void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.today_tab, HomeFragment.newInstance("首页", "1456")); // 这里BlankFragment可以换成其他的 不一定要四个一样的 实际项目中基本不会用到四个一样的
        mFragmentSparseArray.append(R.id.record_tab, BlankFragment.newInstance("订单"));
        mFragmentSparseArray.append(R.id.contact_tab, ShopCarFragment.newInstance("购物车", "564"));
        mFragmentSparseArray.append(R.id.settings_tab, BlankFragment.newInstance("我的"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.today_tab)).commit();
        findViewById(R.id.sign_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllDemoActivity.class));
            }
        });
    }

    //    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            //do something.
//            Toast.makeText(this, "啥的反馈了", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            Toast.makeText(this, "反馈了", Toast.LENGTH_SHORT).show();
//            return super.dispatchKeyEvent(event);
//        }
//    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
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
}
