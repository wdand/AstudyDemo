package com.bingkong.bkbase.constant;

import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.ui.act.BaseView;
import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bknet.http.exception.ApiException;
import com.bingkong.bknet.http.observer.HttpRxObserver;


import io.reactivex.disposables.Disposable;

/**
 * Created by niejiahuan on 18/2/6.
 */

public class Sniffer extends HttpRxObserver {
    private static String TAG = "Sniffer";
    protected Dispatcher mDispatcher;
    protected BaseView mV;
    protected boolean mIsShow;
    protected Object mObject;
    protected String mUsedToken;//The token used for this reque

    public Sniffer(Dispatcher dispatcher, BaseView v, boolean isShow, String tag, String token) {
        super(tag);
        this.mDispatcher = dispatcher;
        this.mV = v;
        mIsShow = isShow;
        mUsedToken = token;
    }

    public Sniffer(Dispatcher dispatcher, BaseView v, boolean isShow, String tag, Object o, String token) {
        this(dispatcher, v, isShow, tag, token);
        mObject = o;
    }

    @Override
    protected void onStart(Disposable d) {
        if (null != this.mV && mIsShow) {
            this.mV.showLoading();
        }
    }

    @Override
    protected void onError(ApiException e) {
        if (null != this.mV && mIsShow) {
            this.mV.hideLoading();
        }
        if (null != this.mV) {
            mV.onError(e.getCode(), e.getMsg());
            LogUtils.loge(TAG, "onError: " + e.getCause());
        }
        mDispatcher.dispatch(mTag,
                Constants.CODE, e.getCode(),
                Constants.MSG, e.getMsg(), Constants.DATA, "", Constants.REQTOKEN, mUsedToken);
    }

    @Override
    protected void onSuccess(Object response) {
        if (null != this.mV && mIsShow) {
            this.mV.hideLoading();
        }
        mDispatcher.dispatch(mTag,
                Constants.CODE, 200,
                Constants.MSG, "", Constants.DATA, response, Constants.OBJECT, mObject, Constants.REQTOKEN, mUsedToken);
    }

}
