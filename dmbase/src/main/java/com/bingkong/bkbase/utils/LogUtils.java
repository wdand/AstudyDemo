package com.bingkong.bkbase.utils;

import android.text.TextUtils;
import android.util.Log;

import com.bingkong.bkbase.utils.log.LogCache;

/**
 * Unokiwi log 统一入口
 * log可以存到本地文件，具体@LogCache
 */
public class LogUtils {
    private static String TAG = "Unokiwi";
    private static boolean mDebug = true;

    public static void setDebug(boolean isDebug) {
        mDebug = isDebug;
        LogCache.getInstance().setDebug(isDebug);
    }

    public static void logd(String log) {
        if (TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.d(TAG, log);
            cacheLog(TAG, log);
        }
    }

    public static void logi(String log) {
        if (TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.i(TAG, log);
            cacheLog(TAG, log);
        }
    }

    public static void loge(String log) {
        if (TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.e(TAG, log);
            cacheLog(TAG, log);
        }
    }

    public static void logd(String tag, String log) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.d(tag, log);
            cacheLog(tag, log);
        }
    }

    public static void logi(String tag, String log) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.i(tag, log);
            cacheLog(tag, log);
        }
    }

    public static void logw(String tag, String log) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.e(tag, log);
            cacheLog(tag, log);
        }
    }

    public static void loge(String tag, String log) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(log)) return;
        if (mDebug) {
            Log.e(tag, log);
            cacheLog(tag, log);
        }
    }

    private static void cacheLog(String tag, String log) {
        LogCache.getInstance().addToCache(tag, "V", log);
    }


}
