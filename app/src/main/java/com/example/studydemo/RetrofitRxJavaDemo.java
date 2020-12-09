package com.example.studydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bingkong.bkbase.base.BaseFluxActivity;
import com.bingkong.bkbase.flux.stores.Store;
import com.bingkong.bkbase.model.LoginResponse;
import com.bingkong.bkbase.model.RegisteredInfo;
import com.bingkong.bknet.http.model.UserInfoModel;
import com.bingkong.bknet.http.retrofit.TokenManager;

public class RetrofitRxJavaDemo extends BaseFluxActivity<StoreDemo,ReqDemo> {



    @Override
    public void initData(Bundle savedInstanceState) {
        Button b = findViewById(R.id.request_button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setText("一二三四456654");
//                actionsCreator().getCaseHistoryListV(RetrofitRxJavaDemo.this, 1,"10");
                actionsCreator().getAllOrder(RetrofitRxJavaDemo.this, 1,10);
            }
        });
        findViewById(R.id.request_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setText("123");
                b.setVisibility(View.VISIBLE);
//                actionsCreator().phoneLogin(RetrofitRxJavaDemo.this, "25933983648842026","e772481acd2c11e98b20fa163e7bddb6","18217433824");
                actionsCreator().yfwLogin(RetrofitRxJavaDemo.this,"wulu","abc123");
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit_rx_java_demo;

    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        if (CombApi.APITAG_USERLOGIN.equals(event.url)) {
            if (event.code == 200) {
                LoginResponse.ResultBean loginResponse = (LoginResponse.ResultBean) event.data;
                UserInfoModel userInfoModel = new UserInfoModel();
                TokenManager.getInstance().setUserInfoModel(userInfoModel);
            }
        }
        else if (CombApi.APITAG_CASEHISTORY.equals(event.url)) {
            if (event.code == 200) {
                CaseDemoBean.ResultBean loginResponse = (CaseDemoBean.ResultBean) event.data;
                UserInfoModel userInfoModel = new UserInfoModel();
                TokenManager.getInstance().setUserInfoModel(userInfoModel);
            }
        }
        else if (CombApi.APITAG_YFW_LOGIN.equals(event.url)) {
            if (event.code == 200) {
                YFWLoginInfo.ResultBean loginResponse = (YFWLoginInfo.ResultBean) event.data;
                UserInfoModel userInfoModel = new UserInfoModel();
                TokenManager.getInstance().setUserInfoModel(userInfoModel);
            }
        }
        else if (CombApi.APITAG_GETORDER.equals(event.url)) {
            if (event.code == 200) {
                OrderInfo.ResultBean loginResponse = (OrderInfo.ResultBean) event.data;
                UserInfoModel userInfoModel = new UserInfoModel();
                TokenManager.getInstance().setUserInfoModel(userInfoModel);
            }
        }
    }


}