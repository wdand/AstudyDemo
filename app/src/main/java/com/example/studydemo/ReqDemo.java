package com.example.studydemo;


import android.content.Intent;

import com.bingkong.bkbase.flux.actions.ActionsCreator;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.ui.act.BaseView;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.trello.rxlifecycle2.LifecycleProvider;

public class ReqDemo extends ActionsCreator {
    public ReqDemo(Dispatcher dispatcher, BaseView view) {
        super(dispatcher, view);
    }
    public void phoneLogin(LifecycleProvider act, String a, String b, String c) {
        reqDate(ServiceManager.create(CombApi.class).phoneLogin(a,b,c),
                act, false, CombApi.APITAG_USERLOGIN, ServiceManager.getUsedToken(CombApi.class));
    }

    public void getCaseHistoryListV(LifecycleProvider act, Integer start, String limt) {
        reqDate(ServiceManager.create(CombApi.class).getCaseHistoryListV(start,limt),
                act, false, CombApi.APITAG_CASEHISTORY, ServiceManager.getUsedToken(CombApi.class));
    }

    public void yfwLogin(LifecycleProvider act, String userName, String passWord) {
        reqDate(ServiceManager.create(CombApi.class).yfwLogin(userName,passWord),
                act, false, CombApi.APITAG_YFW_LOGIN, ServiceManager.getUsedToken(CombApi.class));
    }

    public void getAllOrder(LifecycleProvider act, Integer pageIndex, Integer pageSize) {
        reqDate(ServiceManager.create(CombApi.class).getAllOrder(pageIndex,pageIndex),
                act, false, CombApi.APITAG_GETORDER, ServiceManager.getUsedToken(CombApi.class));
    }

    public void getSoul(LifecycleProvider act) {
        reqDate(ServiceManager.create(CombApi.class).getSoul(),
                act, false, CombApi.APITAG_GETSOUL, ServiceManager.getUsedToken(CombApi.class));
    }
}