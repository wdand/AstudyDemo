package com.example.studydemo.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFluxFragment<StoreDemo, ReqDemo> {
    @BindView(R.id.homeClick)
    TextView add_combination;
    @Override
    public void initBus() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
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