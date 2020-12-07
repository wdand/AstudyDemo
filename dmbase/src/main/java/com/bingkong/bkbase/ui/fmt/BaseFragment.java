package com.bingkong.bkbase.ui.fmt;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bingkong.bkbase.listener.LifeCycleListener;
import com.bingkong.bkbase.ui.act.BaseView;
import com.blankj.utilcode.util.LogUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base fragment.
 *
 * @author JingYeoh
 *         <a href="mailto:yangjing9611@foxmail.com">Email me</a>
 *         <a href="https://github.com/justkiddingbaby">Github</a>
 *         <a href="http://blog.justkiddingbaby.com">Blog</a>
 * @since Nov 22,2017
 */

public abstract class BaseFragment extends RxFragment implements BaseView {

  protected static final String BUNDLE_KEY = "/bundle/key";
  protected View mContentView;
  protected Context mContext;
  protected Unbinder unBinder;
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    if (null==mContentView) {
      mContentView = inflater.inflate(getLayoutId(), container, false);
      unBinder = ButterKnife.bind(this, mContentView);
      LogUtils.d("BaseFragment---" + getClass().getSimpleName());
      setStatusBar();
      initBus();
      initData(savedInstanceState);
      setListener();
    }
    return mContentView;
  }


  public void onFragmentResult(int requestCode, int resultCode, Bundle args){

  }

  @Override
  public Context getContext() {
    return super.getContext();
  }

  LifeCycleListener mListener;

  public void setOnLifeCycleListener(LifeCycleListener lifeCycleListener) {
    this.mListener = lifeCycleListener;
  }
  @Override
  public void onStart() {
    super.onStart();
    if (mListener != null) {
      mListener.onStart();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (mListener != null) {
      mListener.onResume();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if (mListener != null) {
      mListener.onPause();
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    if (mListener != null) {
      mListener.onStop();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (mListener != null) {
      mListener.onDestroy();
    }
    //移除view绑定
    if (unBinder != null) {
      unBinder.unbind();
    }
  }
  //设置状态栏
  public void setStatusBar(){
    //Android6.0（API 23）
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      Window window = getActivity().getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(getResources().getColor(android.R.color.white));
      window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
  }
}
