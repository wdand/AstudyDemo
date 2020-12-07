package com.bingkong.bkbase.flux.actions;


import android.os.Bundle;

import com.bingkong.bkbase.listener.LifeCycleListener;
import com.bingkong.bkbase.ui.act.BaseActivity;
import com.bingkong.bkbase.ui.act.BaseFmtActivity;
import com.bingkong.bkbase.ui.act.BaseView;
import com.bingkong.bkbase.ui.fmt.BaseFragment;

import com.bingkong.bknet.http.observer.HttpRxObservable;
import com.trello.rxlifecycle2.LifecycleProvider;

import com.bingkong.bkbase.constant.Sniffer;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by lgvalle on 02/08/15.
 */
public class ActionsCreator<V extends BaseView, T extends LifeCycleListener> implements LifeCycleListener {
    protected Reference<V> mViewRef;
    protected V mView;
    protected Reference<T> mActivityRef;
    protected T mActivity;
    Map<String, Sniffer> snifferMap = new HashMap<>();
    protected final Dispatcher dispatcher;
    protected final long RETRY_TIMES = 0;

    public ActionsCreator(Dispatcher dispatcher, V v) {
        this.dispatcher = dispatcher;
        this.mView = v;
//        this.mActivity=t;
//        attachActivity(t);
//        attachView(v);
//        setListener(t);
    }

    protected void reqDate(Observable apiObservable, BaseActivity activity, boolean isShow, final String tag, final String usedToken) {
        snifferMap.put(tag, new Sniffer(dispatcher, mView, isShow, tag, usedToken));
        HttpRxObservable.getObservable(apiObservable, activity)
                .subscribe((Observer) snifferMap.get(tag));
    }

    //可以携带参数传回View
    protected void reqDate(Observable apiObservable, BaseActivity activity,
                           boolean isShow, final String tag, Object object, final String usedToken) {
        snifferMap.put(tag, new Sniffer(dispatcher, mView, isShow, tag, object, usedToken));
        HttpRxObservable.getObservable(apiObservable, activity)
                .subscribe((Observer) snifferMap.get(tag));
    }

    //可以携带参数传回View
    protected void reqDate(Observable apiObservable, LifecycleProvider activity,
                           boolean isShow, final String tag, Object object, final String usedToken) {
        snifferMap.put(tag, new Sniffer(dispatcher, mView, isShow, tag, object, usedToken));
        HttpRxObservable.getObservable(apiObservable, activity)
                .subscribe((Observer) snifferMap.get(tag));
    }

    protected void reqDate(Observable apiObservable, BaseFragment fragment,
                           boolean isShow, final String tag, final String usedToken) {
        snifferMap.put(tag, new Sniffer(dispatcher, mView, isShow, tag, usedToken));
        HttpRxObservable.getObservable(apiObservable, fragment)
                .subscribe((Observer) snifferMap.get(tag));
    }

    protected void reqDate(Observable apiObservable, LifecycleProvider lifecycle,
                           boolean isShow, final String tag, final String usedToken) {
        snifferMap.put(tag, new Sniffer(dispatcher, mView, isShow, tag, usedToken));
        HttpRxObservable.getObservable(apiObservable, lifecycle)
                .subscribe((Observer) snifferMap.get(tag));
    }

    /**
     * 设置生命周期监听
     *
     * @author niejiahuan
     */
    private void setListener(T activity) {
        if (getActivity() != null) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) getActivity()).setOnLifeCycleListener(this);
            } else if (activity instanceof BaseFmtActivity) {
                ((BaseFmtActivity) getActivity()).setOnLifeCycleListener(this);
            } else if (activity instanceof BaseFragment) {
                ((BaseFragment) getActivity()).setOnLifeCycleListener(this);
            }
        }
    }

    /**
     * 关联
     *
     * @param view
     */
    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
        mView = mViewRef.get();
    }

    /**
     * 关联
     *
     * @param activity
     */
    private void attachActivity(T activity) {
        mActivityRef = new WeakReference<T>(activity);
        mActivity = mActivityRef.get();
    }

    /**
     * 销毁
     */
    private void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 销毁
     */
    private void detachActivity() {
        if (isActivityAttached()) {
            mActivityRef.clear();
            mActivityRef = null;
        }
    }

    /**
     * 获取
     *
     * @return
     */
    public V getView() {
        if (mViewRef == null) {
            return null;
        }
        return mViewRef.get();
    }

    /**
     * 获取
     *
     * @return
     */
    public T getActivity() {
        if (mActivityRef == null) {
            return null;
        }
        return mActivityRef.get();
    }

    /**
     * 是否已经关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 是否已经关联
     *
     * @return
     */
    public boolean isActivityAttached() {
        return mActivityRef != null && mActivityRef.get() != null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        detachView();
        detachActivity();
    }
}
