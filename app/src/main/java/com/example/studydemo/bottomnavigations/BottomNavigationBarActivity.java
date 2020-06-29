package com.example.studydemo.bottomnavigations;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.studydemo.ArouterConstants;
import com.example.studydemo.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

@Route(path = ArouterConstants.BOTTOM_NACIGATION_PARENT)
public class BottomNavigationBarActivity extends Activity implements View.OnClickListener {
    private Button bottomNavigationView;
    private Button viewpageandradiobutton;
    private Button radiobuttonAndFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);
        initView();
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        String buttonName = getIntent().getStringExtra("buttonName");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewpageandradiobutton = findViewById(R.id.viewpageandradiobutton);
        radiobuttonAndFragment = findViewById(R.id.radiobuttonAndFragment);
        bottomNavigationView.setOnClickListener(this);
        viewpageandradiobutton.setOnClickListener(this);
        radiobuttonAndFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottomNavigationView:
                startActivity(new Intent(BottomNavigationBarActivity.this, BottomNavigationViewAct.class));
                break;
            case R.id.viewpageandradiobutton:
                startActivity(new Intent(BottomNavigationBarActivity.this, ViewPageAndRadioGroupAct.class));
                break;
            case R.id.radiobuttonAndFragment:
                startActivity(new Intent(BottomNavigationBarActivity.this, RadiobuttonAndFragmentAct.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}