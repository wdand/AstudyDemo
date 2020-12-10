package com.bingkong.bkbase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bingkong.bkbase.base.BaseFluxActivity;
import com.bingkong.bkbase.flux.stores.Store;
import com.bingkong.bkbase.model.LoginInfo;
import com.bingkong.bkbase.model.LoginResponse;
import com.bingkong.bknet.http.model.UserInfoModel;
import com.bingkong.bknet.http.retrofit.TokenManager;

public class LoginAct extends BaseFluxActivity<LoginStore,ReqLogin> implements View.OnClickListener{



    @Override
    public void initData(Bundle savedInstanceState) {
        findViewById(R.id.btn_login).setOnClickListener(this);

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login){
            actionsCreator().yfwLogin(LoginAct.this,"wulu","wulu888");
//            actionsCreator().yfwLogin(LoginAct.this,loginInfo);
        }
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
         if (LoginApi.APITAG_YFW_LOGIN.equals(event.url)) {
            if (event.code == 200) {
                YFWLoginInfoRes.ResultBean loginResponse = (YFWLoginInfoRes.ResultBean) event.data;
                UserInfoModel userInfoModel = new UserInfoModel();
                TokenManager.getInstance().setUserInfoModel(userInfoModel);
            }
        }

    }
}