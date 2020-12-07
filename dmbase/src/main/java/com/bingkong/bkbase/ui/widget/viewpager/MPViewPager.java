package com.bingkong.bkbase.ui.widget.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bingkong.bkbase.ui.widget.tablayout.MPViewPagerFixed;

public class MPViewPager extends MPViewPagerFixed {

    //横向滑动阀值
    private static final float X_THRESHOLD = 10.0f;

    private boolean mCanScroll;
    private float mPointX;
    private float mPointY;

    /**
     * 是否需要支持嵌套Grid布局横滑控制
     */
    private boolean mSupportGrid = false;

    public MPViewPager(Context context) {
        super(context);
        mCanScroll = true;
    }

    public MPViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCanScroll = true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mCanScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            if (mCanScroll) {
                if (mSupportGrid) {
                    switch (ev.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            mPointX = ev.getX();
                            mPointY = ev.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float xOffset = Math.abs(ev.getX() - mPointX);
                            float yOffset = Math.abs(ev.getY() - mPointY);
                            if (xOffset > yOffset
                                    && xOffset > X_THRESHOLD) {//当滑动事件为横滑，且达到一定阀值则消费掉该滑动事件不向子view传递
                                super.onInterceptTouchEvent(ev);
                                return true;
                            }
                            break;
                        default:
                            break;
                    }
                }
                return super.onInterceptTouchEvent(ev);
            } else {
                super.onInterceptTouchEvent(ev);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

