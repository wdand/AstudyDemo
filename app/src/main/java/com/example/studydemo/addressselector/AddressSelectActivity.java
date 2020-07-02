package com.example.studydemo.addressselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.studydemo.ArouterConstants;
import com.example.studydemo.R;

@Route(path = ArouterConstants.ADDRESS_SELECT_ACT)
public class AddressSelectActivity extends AppCompatActivity {

    TextView mTvSlect;
    TextView mTvCity;
    JDCityPicker mJDCityPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select);
        mTvSlect = findViewById(R.id.tv_select);
        mTvCity = findViewById(R.id.tv_city_pick);
        mTvSlect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgAlpha(0.7f);
                mJDCityPicker = new JDCityPicker(AddressSelectActivity.this, new JDCityPicker.onCitySelect() {
                    @Override
                    public void onSelect(String province, String city, String area) {
                        mTvCity.setText(province + "   " + city + "   " + area);
                    }
                });
                mJDCityPicker.showAtLocation(mTvSlect, Gravity.BOTTOM, 0, 0);

                mJDCityPicker.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);
                    }
                });
            }

        });

    }

    //背景变暗
    private void bgAlpha(float f) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }
}