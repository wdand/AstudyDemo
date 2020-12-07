package com.bingkong.bkbase.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bingkong.bkbase.R;
import com.bingkong.bkbase.app.bkStockApp;

public class CommonToast extends Toast {

    /**
     * 图标状态 不显示图标
     */
    private static final int TYPE_HIDE = -1;
    /**
     * 图标状态 显示√
     */
    private static final int TYPE_TRUE = 0;
    /**
     * 图标状态 显示×
     */
    private static final int TYPE_FALSE = 1;

    /**
     * Toast单例
     */
    private static CommonToast toast;

    /**
     * Toast所显示的图片
     */
    private static ImageView toast_img;

    /**
     * 构造
     *
     * @param context
     */
    public CommonToast(Context context) {
        super(context);
    }

    /**
     * 显示一个纯文本, context使用ApplicationContext
     * 推荐使用该方法，如果用activity context, 可能导致文字过长时，被截断
     * 见 https://stackoverflow.com/questions/7755204/android-toast-text-gets-cut-off/50914679#
     *
     * @param text 显示的文本
     */
    public static void showText(CharSequence text) {
        showToast(bkStockApp.getContext(), text, Toast.LENGTH_SHORT, TYPE_HIDE);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    public static void showText(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT, TYPE_HIDE);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, boolean isSucceed) {
        showToast(context, text, Toast.LENGTH_SHORT, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context
     * @param text    显示的文本
     * @param time    持续的时间
     */
    public static void showText(Context context, CharSequence text, int time) {
        showToast(context, text, time, TYPE_HIDE);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context
     * @param text      显示的文本
     * @param time      持续的时间
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, int time, boolean isSucceed) {
        showToast(context, text, time, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param text    显示的文本
     * @param time    显示时长
     * @param imgType 图标状态
     */
    private static void showToast(final Context context, final CharSequence text, final int time, final int imgType) {
        Handler mMainHandler = new Handler(Looper.getMainLooper());
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (text.toString().contains("Bitmap")) {
                    Exception e = new Exception("Bitmap.isRecycled error");
                    e.printStackTrace();
                }
                if (text.toString().contains("param")) {
                    Exception e = new Exception("parameter error!");
                    e.printStackTrace();
                }

                if (text.toString().contains("Invalid")) {
                    Exception e = new Exception("parameter error!");
                    e.printStackTrace();
                }

                // 初始化一个新的Toast对象
                initToast(context, text);

                // 设置显示时长
                if (time == Toast.LENGTH_LONG) {
                    toast.setDuration(Toast.LENGTH_LONG);
                } else {
                    toast.setDuration(Toast.LENGTH_SHORT);
                }

                // 判断图标是否该显示，显示√还是×
                if (imgType == TYPE_HIDE) {
                    toast_img.setVisibility(View.GONE);
                } else {
                    if (imgType == TYPE_TRUE) {
                        toast_img.setBackgroundResource(R.drawable.clear_all);
                    } else {
                        toast_img.setBackgroundResource(R.drawable.clear_all);
                    }
                    toast_img.setVisibility(View.VISIBLE);
                    ObjectAnimator.ofFloat(toast_img, "rotationY", 0, 360).setDuration(1700).start();
                }

                // 显示Toast
                toast.show();
            }
        });
    }

    /**
     * 初始化Toast
     *
     * @param context
     * @param text    显示的文本
     */
    private static void initToast(Context context, CharSequence text) {
        try {
            cancelToast();

            toast = new CommonToast(context);
            // 获取LayoutInflater对象
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 由layout文件创建一个View对象
            View layout = inflater.inflate(R.layout.toast_layout, null);

            // 吐司上的图片
            toast_img = (ImageView) layout.findViewById(R.id.toast_img);

            // 实例化ImageView和TextView对象
            TextView toast_text = (TextView) layout.findViewById(R.id.toast_text);
            toast_text.setText(text);
            toast.setView(layout);
            toast.setGravity(Gravity.CENTER, 0, 70);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏当前Toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {

        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {

        }
    }

}