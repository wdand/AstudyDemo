package com.bingkong.bkbase.utils;

import android.content.Context;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bingkong.bkbase.R;

/**
 * Author:xianglei
 * Date: 2019/4/24 3:08 PM
 * Description:
 */
public class GlideUtil {

    private static final int PLACEHOLDER = R.mipmap.imgloadfailure;
    private static final int ERROR = R.mipmap.imgloadfailure;

    //圆形图
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context) // 指定Context
                .load(url)// 指定图片的URL
                .error(ERROR)
                .placeholder(PLACEHOLDER)
                .centerCrop()
                .into(imageView);//指定显示图片的ImageView
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context) // 指定Context
                .load(url)// 指定图片的URL
                .centerCrop()
                .error(ERROR)
                .placeholder(PLACEHOLDER).into(imageView);//指定显示图片的ImageView
    }
}
