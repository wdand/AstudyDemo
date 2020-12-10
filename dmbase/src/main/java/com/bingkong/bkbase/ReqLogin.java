package com.bingkong.bkbase;

import com.bingkong.bkbase.flux.actions.ActionsCreator;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.ui.act.BaseView;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.trello.rxlifecycle2.LifecycleProvider;

public class ReqLogin extends ActionsCreator {

    public ReqLogin(Dispatcher dispatcher, BaseView view) {
        super(dispatcher, view);
    }



    public void yfwLogin(LifecycleProvider act, String userName, String passWord) {
        reqDate(ServiceManager.create(LoginApi.class).yfwLogin(userName,passWord),
                act, false, LoginApi.APITAG_YFW_LOGIN, ServiceManager.getUsedToken(LoginApi.class));
    }
}