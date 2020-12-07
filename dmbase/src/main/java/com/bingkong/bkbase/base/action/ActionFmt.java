package com.bingkong.bkbase.base.action;


import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.bingkong.bknet.http.retrofit.TokenManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.bingkong.bkbase.ui.fmt.BaseFragment;
import com.bingkong.bkbase.constant.Constants;
import com.bingkong.bkbase.utils.CommonUtil;
import com.bingkong.bkbase.utils.DialogUtil;
import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bkbase.utils.LoginDialog;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * A simple {@link ActionFmt} subclass.
 */
public abstract class ActionFmt extends BaseFragment {
    private Toast mToast;
    private LoginDialog mProgressDialog;
    private static String TAG = "ActionFmt";

    @Override
    public void showToast(String msg) {
        String msgNew = msg;
        if (msg.contains("Bitmap.isRecycled")) {
            LogUtils.loge("ActionAct", "showToast: error");
            Exception e = new Exception("Bitmap.isRecycled error");
            e.printStackTrace();
        }
        if (msg.contains("Network Timeout")) {
            LogUtils.loge("ActionAct", "NetWork Timeout!");
        }
        if (msg.contains("token") || (msg.contains("Token"))) {
            LogUtils.loge(TAG, "ERROR showToast: " + msg);
            msgNew = "Please login firstly for more action.";
        }
        if (mToast == null) {
            mToast = Toast.makeText(this.getActivity(), msgNew, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msgNew);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    protected void showProgressDialog() {
        showProgressDialog("loading...");
    }

    protected void showProgressDialog(String message) {
        showProgressDialog(message, true);
    }

    protected void showProgressDialog(String message, boolean cancelable) {
        if (mProgressDialog == null)
            mProgressDialog = DialogUtil.createProgressDialog(this.getActivity(), message, cancelable);
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
    protected <T extends Object> Observable<T> bindUntil(Observable<T> observable, FragmentEvent event) {
        return (Observable<T>) observable.compose(bindUntilEvent(event));
    }

    @Override
    public void onError(int code, String msg) {
        if (code == 101 || code == 102) {
            ServiceManager.clearMap();
            TokenManager.getInstance().clearToken();
            showToast(msg);
            ARouter.getInstance().build(Constants.LOGIN_ACT).navigation();
        } else {
            if (null != msg && !TextUtils.isEmpty(msg)) {
                LogUtils.loge(TAG, "onError: Serial error" + msg);
                if (!CommonUtil.isWorkOnProductServer(getContext())) {
                    showToast(msg);
                }
            }
        }
    }

    public ActionFmt getFragment(String fTag) {
        return (ActionFmt) ARouter.getInstance().build(fTag).navigation();
    }

    public void startActivity(String aTag) {
        ARouter.getInstance().build(aTag).navigation();
    }
}
