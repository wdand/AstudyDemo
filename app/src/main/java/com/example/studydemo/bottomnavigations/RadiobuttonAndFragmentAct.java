package com.example.studydemo.bottomnavigations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;

import com.example.studydemo.R;

public class RadiobuttonAndFragmentAct extends AppCompatActivity {

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton_and_fragment);
        initView();
    }

    private void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.today_tab, BlankFragment.newInstance("首页")); // 这里BlankFragment可以换成其他的 不一定要四个一样的 实际项目中基本不会用到四个一样的
        mFragmentSparseArray.append(R.id.record_tab, BlankFragment.newInstance("订单"));
        mFragmentSparseArray.append(R.id.contact_tab, BlankFragment.newInstance("购物车"));
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
                startActivity(new Intent(RadiobuttonAndFragmentAct.this, HelloWorld.class));
            }
        });
    }
}
