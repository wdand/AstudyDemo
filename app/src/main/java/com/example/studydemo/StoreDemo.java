package com.example.studydemo;


import com.bingkong.bkbase.flux.annotation.BindAction;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.flux.stores.Store;

import java.util.HashMap;

public class StoreDemo extends Store {
    public StoreDemo(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @BindAction(CombApi.APITAG_USERLOGIN)
    public void userLogin(HashMap<String, Object> data) {
        emitStoreChange(CombApi.APITAG_USERLOGIN, data);
    }
}