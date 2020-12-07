package com.bingkong.bkbase.ui.widget.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

@SuppressLint("ValidFragment")
public class BaseConfirmDialog {

    public BaseConfirmDialog() {
    }

    public interface OnConfirmListener {
        void onClick(Context context, int which);

        void onDismiss();
    }

    /**
     * 展示一个可添加多个按钮的对话框。
     *
     * @param title    对话框标题
     * @param items    按钮文本数组
     * @param cancel   是否支持点击外部取消
     * @param listener 按钮点击事件监听
     * @return 返回一个对话框实例
     */
    public static void show(Context context, String title, String[] items, boolean cancel, OnConfirmListener listener) {
        ConfirmDialog.OnConfirmListener listenerTmp;
        final OnConfirmListener oldListener = listener;
        listenerTmp = new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onClick(Context context, int index) {
                if (oldListener != null) {
                    oldListener.onClick(context, index);
                }
            }
        };
        DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (oldListener != null) {
                    oldListener.onDismiss();
                }
            }
        };
        new ConfirmDialog.Builder().setDescription(title)
                .setButtonItems(items).setCancelable(cancel).setConfirmListener(listenerTmp)
                .setOnDismissListener(dismissListener).show(context);
    }

    /**
     * 展示一个可添加多个按钮的对话框。
     *
     * @param title    对话框标题
     * @param items    按钮文本数组
     * @param colors   按钮文本颜色，0代表使用默认色（注意位置需与items对应）
     * @param cancel   是否支持点击外部取消
     * @param listener 按钮点击事件监听
     * @return 返回一个对话框实例
     */
    public static void show(Context context, String title, String[] items, int[] colors, boolean cancel, OnConfirmListener listener) {
        show(context, title, items, cancel, listener);
    }
}
