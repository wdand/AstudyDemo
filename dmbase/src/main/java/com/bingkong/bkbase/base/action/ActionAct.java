package com.bingkong.bkbase.base.action;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bingkong.bkbase.R;
import com.bingkong.bkbase.ui.act.BaseActivity;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.bingkong.bknet.http.retrofit.TokenManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.bingkong.bkbase.constant.Constants;
import com.bingkong.bkbase.ui.view.CommonToast;
import com.bingkong.bkbase.utils.CommonUtil;
import com.bingkong.bkbase.utils.DialogUtil;
import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bkbase.utils.LoginDialog;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;
import io.reactivex.Observable;

public abstract class ActionAct extends BaseActivity {
    private static String TAG = ActionAct.class.getSimpleName();
    private Toast mToast;
    private LoginDialog mProgressDialog;
    protected Toolbar mToolbar;
    protected TextView mTitleTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }


    public void showToast(String msg) {
        if (msg.contains("Bitmap")) {
            LogUtils.loge("ActionAct", "showToast: error");
            Exception e = new Exception("Bitmap.isRecycled error");
            e.printStackTrace();
        }
        if (msg.equals("unknown error")) {
            LogUtils.loge("ActionAct", "showToast: " + msg);
        } else {
            CommonToast.showText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
    }

    protected void initToolbar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mTitleTv.setText(title);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // finish();
            }
        });
    }

    protected void initToolbar(String title, int index) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mTitleTv.setText(title);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.back_icon);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//                finish();
//            }
//        });
    }

    protected void showProgressDialog() {
        showProgressDialog("Loading...");
    }

    protected void showProgressDialog(String message) {
        showProgressDialog(message, true);
    }

    protected void showProgressDialog(String message, boolean cancelable) {
        if (mProgressDialog == null)
            mProgressDialog = DialogUtil.createProgressDialog(this, message, cancelable);
        if (mProgressDialog.isShowing()) {
            hideDialog();
            return;
        } else {
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void hideDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }


    public Observable click(View view) {
        return throttleFirst(RxView.clicks(view));
    }

    /**
     * 防抖动，防止快速点击
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends Object> Observable<T> throttleFirst(Observable<T> observable) {
        return observable.throttleFirst(30000, TimeUnit.MILLISECONDS);
    }

    /**
     * 将事件与生命周期绑定
     *
     * @param observable
     * @return
     */
    protected <T extends Object> Observable<T> bindLife(Observable<T> observable) {
        return (Observable<T>) observable.compose(bindToLifecycle());
    }

    /**
     * 指定事件在哪个生命周期结束
     *
     * @param observable
     * @param event      生命周期
     * @return
     */
    protected <T extends Object> Observable<T> bindUntil(Observable<T> observable, ActivityEvent event) {
        return (Observable<T>) observable.compose(bindUntilEvent(event));
    }

    @Override
    public void onError(int code, String msg) {
        if (code == 101 || code == 102) {
            ServiceManager.clearMap();
            TokenManager.getInstance().clearToken();
            if (!CommonUtil.isWorkOnProductServer(getContext())) {
                showToast(msg);
            }
            ARouter.getInstance().build(Constants.LOGIN_ACT).navigation();
        } else {
            if (null != msg && !TextUtils.isEmpty(msg)) {
                if (!msg.equals("unknown error")) {
                    LogUtils.loge(TAG, "onError: " + msg);
                    if (!CommonUtil.isWorkOnProductServer(getContext())) {
                        showToast(msg);
                    }
                }
            }
        }
    }

    public Fragment getFragment(String fTag) {
        return (Fragment) ARouter.getInstance().build(fTag).navigation();
    }
}
