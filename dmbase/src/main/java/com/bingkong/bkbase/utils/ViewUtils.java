package com.bingkong.bkbase.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

public class ViewUtils {
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //px：pixel，像素，电子屏幕上组成一幅图画或照片的最基本单元
    //pt: point，点，印刷行业常用单位，等于1/72英寸
    //ppi: pixel per inch，每英寸像素数，该值越高，则屏幕越细腻
    //dpi: dot per inch，每英寸多少点，该值越高，则图片越细腻
    //dp: dip，Density-independent pixel, 是安卓开发用的长度单位，1dp表示在屏幕像素点密度为160ppi时1px长度
    //sp: scale-independent pixel，安卓开发用的字体大小单位。
    //1pt= (DPI / 72) px
    //1dp=（屏幕ppi/ 160）px
    //Normally 1 ppi=1 DPI.
    //ppi= 屏幕对角线上的像素点数/对角线长度 = √ （屏幕横向像素点^2 + 屏幕纵向像素点^2）/对角线长度

    public static int pt2px(Context context, float ptValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        float dpValue = 0.45F * ptValue;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /*
     *  px to dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void setViewActiviated(View view) {
        if (view != null) {
            view.setActivated(true);
            view.setSelected(false);
        }
    }

    /**
     * 主要用于view有常规3种状态的，如如deisign的绘制，橡皮擦，缩放
     *
     * @param view
     */
    public static void setViewToNormalStatus(View view) {
        if (view != null) {
            view.setActivated(false);
            view.setSelected(true);
        }
    }

    public static void setViewToNormalStatus(View... viewList) {
        for (View view : viewList) {
            setViewToNormalStatus(view);
        }
    }

    public static void setViewAbility(boolean enable, View... viewList) {
        for (View view : viewList) {
            setViewCapability(view, enable);
        }
    }

    /**
     * 用于常规有2种状态，仅按下时会高亮的情况，如deisign的删除，1：1按钮
     *
     * @param view
     */
    public static void setViewCapability(View view, boolean enable) {
        if (view != null) {
            view.setAlpha(enable ? 1.0f : 0.25f);
            view.setClickable(enable);
        }
    }

    public static Boolean isTransparent(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        int a = Color.alpha(color);
        if (a == 0) {
            return true;
        }
        return false;
    }
}
