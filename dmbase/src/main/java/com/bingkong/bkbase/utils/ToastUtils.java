package com.bingkong.bkbase.utils;

import android.content.Context;
import android.widget.Toast;

import com.bingkong.bkbase.ui.view.CommonToast;

public class ToastUtils {

    public static void showToast(Context context, final String messageToShow) {
        CommonToast.showText(context.getApplicationContext(), messageToShow, Toast.LENGTH_SHORT);

    }
}
