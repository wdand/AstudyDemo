package com.bingkong.bkbase.utils;

import android.content.Context;
import android.content.DialogInterface;

import android.widget.Toast;


import com.bingkong.bkbase.ui.view.CommonToast;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by Snow on 2016/11/7.
 * Description:
 */

public class DialogUtil {
    private DialogUtil() {
    }


    public static void showToast(Context context, String msg) {
        CommonToast.showText(context, msg, Toast.LENGTH_SHORT);
    }

    public static LoginDialog createProgressDialog(Context context, String message, boolean cancelable) {
        LoginDialog progressDialog = new LoginDialog(context);
        if (message != null) {
            progressDialog.setMessage(message);
        }
        return progressDialog;
    }

    public static AlertDialog createAlertDialog(Context context, String title, String message, String positive, String negative, boolean cancelable, final DialogButtonClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeButtonClick(dialog);
            }
        });
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositiveButtonClick(dialog);
            }
        });
        builder.setCancelable(cancelable);
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    /**
     * 使用系统的AlertDialog， 而不是appcompat包的，如果用appcompat，那么activity就要使用AppCompat主题
     */
    public static android.app.AlertDialog createNormalAlertDialog(Context context, String title, String message, String positive, String negative, boolean cancelable, final DialogButtonClickListener listener) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeButtonClick(dialog);
            }
        });
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositiveButtonClick(dialog);
            }
        });
        builder.setCancelable(cancelable);
        android.app.AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public interface DialogButtonClickListener {
        void onPositiveButtonClick(DialogInterface dialog);

        void onNegativeButtonClick(DialogInterface dialog);
    }

}
