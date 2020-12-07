package com.bingkong.bkbase.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;


/**
 * use WeakREference to implement handler to avoid the memory heak caused by handler holded by
 * as property. The paramer callback should have some lifecycle as Handler otherwise the Callback will
 * be released immediatly.
 * <p>
 * About usage, please reference the following example.
 * private Handler.Callback mCallback = new Handler.Callback() {
 *
 * @Override public boolean handleMessage(Message msg) {
 * switch(msg.what){
 * }
 * return true;
 * }
 * };
 * private Handler mHandler = new WeakRefHandler(mCallback);
 */
public class WeakRefHandler extends Handler {
    private WeakReference<Callback> mWeakReference;

    public WeakRefHandler(Callback callback) {
        mWeakReference = new WeakReference<Handler.Callback>(callback);
    }

    public WeakRefHandler(Callback callback, Looper looper) {
        super(looper);
        mWeakReference = new WeakReference<Handler.Callback>(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mWeakReference != null && mWeakReference.get() != null) {
            Callback callback = mWeakReference.get();
            callback.handleMessage(msg);
        }
    }
}
