package com.example.studydemo.selectcolorview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bkbase.utils.ViewUtils;
import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.studydemo.R;

/**
 * 单一颜色的原图，用作画板
 */
public class SelectColotCircleView extends View {
    private Paint mPaint;
    private String colorStr = "#000000";
    private static final String TAG = "CircleView";
    private ProductColorData materialData;
    private boolean selected = false;
    private Paint mStrokePaint;
    private Context mContext;
    public SelectColotCircleView(Context context) {
        this(context,null);
        configPaint();
    }

    public SelectColotCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        configPaint();
    }

    public SelectColotCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configPaint();
    }

    public void setData(ProductColorData materialData) {
        this.materialData = materialData;

    }

    public ProductColorData getData() {
        return materialData;
    }

    public void setColor(String colorStr) {
        if(colorStr!=null) {
            if(!colorStr.contains("#")){
                colorStr="#"+colorStr;
            }
        }
        this.colorStr = colorStr;
    }
    public String getColor() {
        return this.colorStr;
    }

    public void setSelected(boolean selected,Context context) {
        this.selected = selected;
        this.mContext = context;
        invalidate();
    }

    private void configPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//开启抗锯齿，平滑文字和圆弧的边缘
        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStrokeWidth(ViewUtils.dip2px(getContext(), 1));
        mStrokePaint.setColor(0xFF757778);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;//获取宽度一半
        int centerY = getHeight() / 2;//获取高度一半
        int radius = Math.min(centerX, centerY);//设置半径为宽或者高的最小值
        if(materialData != null && !TextUtils.isEmpty(materialData.getPath())) {
//            LogUtils.logd(TAG, "onDraw " + materialData.getPath());
            initShaderToPaint(materialData.getPath());
        } else if(materialData != null && !TextUtils.isEmpty(materialData.getColor())) {
//            LogUtils.logd(TAG, "onDraw " + materialData.getColor());

            mPaint.setColor(Color.parseColor(materialData.getColor()));//设置画笔颜色
        } else if(ColorPlatte.TRANSPARENT_COLOR.equals(colorStr)) {
//            LogUtils.logd(TAG, "onDraw " + colorStr);
            //如果是透明色
            initShaderToPaint();
        } else if(!TextUtils.isEmpty(colorStr)) {
//            LogUtils.logd(TAG, "onDraw " + colorStr);
            mPaint.setColor(Color.parseColor(colorStr));//设置画笔颜色
        }

        int innerRadius = radius - ViewUtils.dip2px(getContext(), 3);//选中变大
        int strokeRadius = radius - ViewUtils.dip2px(getContext(), 1);//选中，则画一个外圈

        if(selected) {
            canvas.drawCircle(centerX, centerY, innerRadius, mPaint);//内圆
            canvas.drawCircle(centerX, centerY, strokeRadius, mStrokePaint);//外圆圈

        } else {
            canvas.drawCircle(centerX, centerY, innerRadius, mPaint);//内圆
        }
    }

    private void initShaderToPaint() {
        try {
            Bitmap shaderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.editdesign_erazor);
            BitmapShader shader = new BitmapShader(shaderBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(shader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initShaderToPaint(String bitmapPath) {
//        LogUtils.logd(TAG, "initShaderToPaint " + bitmapPath);
//        try {
//            Glide.with(getContext()).load(bitmapPath).asBitmap().centerCrop().into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
//                    BitmapShader shader = new BitmapShader(resource, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//                    mPaint.setShader(shader);
//                    invalidate();
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}