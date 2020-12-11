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

    @BindAction(CombApi.APITAG_CASEHISTORY)
    public void getCaseHistoryListV(HashMap<String, Object> data) {
        emitStoreChange(CombApi.APITAG_CASEHISTORY, data);
    }

    @BindAction(CombApi.APITAG_YFW_LOGIN)
    public void yfwLogin(HashMap<String, Object> data) {
        emitStoreChange(CombApi.APITAG_YFW_LOGIN, data);
    }

    @BindAction(CombApi.APITAG_GETORDER)
    public void getAllOrder(HashMap<String, Object> data) {
        emitStoreChange(CombApi.APITAG_GETORDER, data);
    }

    @BindAction(CombApi.APITAG_GETSOUL)
    public void getSoul(HashMap<String, Object> data) {
        emitStoreChange(CombApi.APITAG_GETSOUL, data);
    }

    @BindAction(CombApi.APITAG_GETHOEMDATA)
    public void getHomeData(HashMap<String, Object> data) {
        emitStoreChange(CombApi.APITAG_GETHOEMDATA, data);
    }
}