package com.example.studydemo;


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
}