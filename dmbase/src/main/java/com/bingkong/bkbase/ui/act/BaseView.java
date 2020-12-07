package com.bingkong.bkbase.ui.act;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.LayoutRes;

public interface BaseView {
    void initBus();
    void initData(Bundle savedInstanceState);

    void setListener();
    @LayoutRes
    int getLayoutId();

    Context getContext();

    void showToast(String msg);

    void showLoading();

    void hideLoading();

    void onError(int code, String msg);
}