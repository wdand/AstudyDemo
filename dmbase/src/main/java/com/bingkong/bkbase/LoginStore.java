package com.bingkong.bkbase;

import com.bingkong.bkbase.flux.annotation.BindAction;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.flux.stores.Store;

import java.util.HashMap;

public class LoginStore extends Store {
    public LoginStore(Dispatcher dispatcher) {
        super(dispatcher);
    }


    @BindAction(LoginApi.APITAG_YFW_LOGIN)
    public void yfwLogin(HashMap<String, Object> data) {
        emitStoreChange(LoginApi.APITAG_YFW_LOGIN, data);
    }

}