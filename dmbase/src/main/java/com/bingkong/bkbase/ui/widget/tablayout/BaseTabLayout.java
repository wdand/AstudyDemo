package com.bingkong.bkbase.ui.widget.tablayout;


import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bingkong.bkbase.R;
import com.bingkong.bkbase.utils.LogUtils;

import java.util.Locale;

public abstract class BaseTabLayout extends HorizontalScrollView implements
        ValueAnimator.AnimatorUpdateListener {
    public static final String TAG = BaseTabLayout.class.getSimpleName();
    public static final int INDICATOR_NORMAL = 1;
    private static final int INDICATOR_DAMPING = 2;
    protected Locale locale;
    protected Context mContext;
    protected LinearLayout mTabsContainer;
    protected int mCurrentTab;
    protected int mLastTab;
    protected int mTabCount;
    /**
     * 用于绘制显示器
     */
    private Rect mIndicatorRect = new Rect();
    private GradientDrawable mIndicatorDrawable = new GradientDrawable();

    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mTrianglePath = new Path();
    private static final int STYLE_NORMAL = 0;
    private static final int STYLE_TRIANGLE = 1;
    private static final int STYLE_BLOCK = 2;
    protected int mIndicatorAnimStyle = INDICATOR_DAMPING;

    protected float mTabPadding;
    protected boolean mTabSpaceEqual;
    protected float mTabWidth;

    protected int mTabBackgroundResId;

    /**
     * indicator
     */
    private int mIndicatorColor = 0xFF666666;
    private float mIndicatorHeight;
    private float mIndicatorWidth;
    private float mIndicatorCornerRadius;
    private float mIndicatorMarginLeft;
    private float mIndicatorMarginTop;
    private float mIndicatorMarginRight;
    private float mIndicatorMarginBottom;
    private long mIndicatorAnimDuration;
    protected boolean mIndicatorAnimEnable;
    private boolean mIndicatorBounceEnable;
    private int mIndicatorGravity;
    private int mIndicatorStyle = STYLE_NORMAL;

    /**
     * underline
     */
    private int mUnderlineColor;
    private float mUnderlineHeight;
    private int mUnderlineGravity;

    /**
     * divider
     */
    private int mDividerColor;
    private float mDividerWidth;
    private float mDividerPadding;

    /**
     * title
     */
    protected float mTextsize;
    protected int mTextSelectColor;
    protected int mTextUnselectColor;
    protected boolean mTextBold;
    protected boolean mTextAllCaps;

    /**
     * icon
     */
    protected boolean mIconVisible;
    protected int mIconGravity;
    protected float mIconWidth;
    protected float mIconHeight;
    protected float mIconMargin;

    protected int mHeight;

    /**
     * anim
     */
    private ValueAnimator mValueAnimator;
    private OvershootInterpolator mInterpolator = new OvershootInterpolator(1.5f);

    private boolean mIndicatorDamping = true;
    private boolean checkedMoveLeft = true;

    public BaseTabLayout(Context context) {
        this(context, null, 0);
    }

    public BaseTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFillViewport(true);
        setWillNotDraw(false);//重写onDraw方法,需要调用这个方法来清除flag
        setClipChildren(false);
        setClipToPadding(false);

        this.mContext = context;
        mTabsContainer = new LinearLayout(context);
        addView(mTabsContainer);
        obtainAttributes(context, attrs);
        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
        //get layout_height
        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android",
                "layout_height");

        //create ViewPager
        if (height.equals(ViewGroup.LayoutParams.MATCH_PARENT + "")) {
            LogUtils.logd(TAG, "height =" + height);
        } else if (height.equals(ViewGroup.LayoutParams.WRAP_CONTENT + "")) {
            LogUtils.logd(TAG, "height =" + height);
        } else {
            int[] systemAttrs = {android.R.attr.layout_height};
            TypedArray a = context.obtainStyledAttributes(attrs, systemAttrs);
            mHeight = a.getDimensionPixelSize(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            a.recycle();
        }

        mValueAnimator = ValueAnimator.ofObject(new PointEvaluator(), mLastP, mCurrentP);
        mValueAnimator.addUpdateListener(this);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BaseTabLayout);

        mIndicatorStyle = ta.getInt(R.styleable.BaseTabLayout_tl_indicator_style, 0);
        mIndicatorColor = ta.getColor(R.styleable.BaseTabLayout_tl_indicator_color,
                Color.parseColor(mIndicatorStyle == STYLE_BLOCK ? "#4B6A87" : "#ffffff"));
        mIndicatorHeight = ta.getDimension(R.styleable.BaseTabLayout_tl_indicator_height,
                dp2px(mIndicatorStyle == STYLE_TRIANGLE ? 4
                        : (mIndicatorStyle == STYLE_BLOCK ? -1 : 2)));
        mIndicatorWidth = ta.getDimension(R.styleable.BaseTabLayout_tl_indicator_width,
                dp2px(mIndicatorStyle == STYLE_TRIANGLE ? 10 : -1));
        mIndicatorCornerRadius = ta.getDimension(
                R.styleable.BaseTabLayout_tl_indicator_corner_radius,
                dp2px(mIndicatorStyle == STYLE_BLOCK ? -1 : 0));
        mIndicatorMarginLeft = ta.getDimension(
                R.styleable.BaseTabLayout_tl_indicator_margin_left, dp2px(0));
        mIndicatorMarginTop = ta.getDimension(R.styleable.BaseTabLayout_tl_indicator_margin_top,
                dp2px(mIndicatorStyle == STYLE_BLOCK ? 7 : 0));
        mIndicatorMarginRight = ta.getDimension(
                R.styleable.BaseTabLayout_tl_indicator_margin_right, dp2px(0));
        mIndicatorMarginBottom = ta.getDimension(
                R.styleable.BaseTabLayout_tl_indicator_margin_bottom,
                dp2px(mIndicatorStyle == STYLE_BLOCK ? 7 : 0));
        mIndicatorAnimEnable = ta.getBoolean(R.styleable.BaseTabLayout_tl_indicator_anim_enable,
                false);
        mIndicatorBounceEnable = ta.getBoolean(
                R.styleable.BaseTabLayout_tl_indicator_bounce_enable, true);
        mIndicatorAnimDuration = ta.getInt(R.styleable.BaseTabLayout_tl_indicator_anim_duration,
                -1);
        mIndicatorGravity = ta.getInt(R.styleable.BaseTabLayout_tl_indicator_gravity,
                Gravity.BOTTOM);

        mUnderlineColor = ta.getColor(R.styleable.BaseTabLayout_tl_underline_color,
                Color.parseColor("#ffffff"));
        mUnderlineHeight = ta.getDimension(R.styleable.BaseTabLayout_tl_underline_height,
                dp2px(0));
        mUnderlineGravity = ta.getInt(R.styleable.BaseTabLayout_tl_underline_gravity,
                Gravity.BOTTOM);

        mDividerColor = ta.getColor(R.styleable.BaseTabLayout_tl_divider_color,
                Color.parseColor("#ffffff"));
        mDividerWidth = ta.getDimension(R.styleable.BaseTabLayout_tl_divider_width, dp2px(0));
        mDividerPadding = ta.getDimension(R.styleable.BaseTabLayout_tl_divider_padding,
                dp2px(12));

        mTextsize = ta.getDimension(R.styleable.BaseTabLayout_tl_textsize, sp2px(13f));
        mTextSelectColor = ta.getColor(R.styleable.BaseTabLayout_tl_textSelectColor,
                Color.parseColor("#ffffff"));
        mTextUnselectColor = ta.getColor(R.styleable.BaseTabLayout_tl_textUnselectColor,
                Color.parseColor("#AAffffff"));
        mTextBold = ta.getBoolean(R.styleable.BaseTabLayout_tl_textBold, false);
        mTextAllCaps = ta.getBoolean(R.styleable.BaseTabLayout_tl_textAllCaps, false);

        mIconVisible = ta.getBoolean(R.styleable.BaseTabLayout_tl_iconVisible, false);
        mIconGravity = ta.getInt(R.styleable.BaseTabLayout_tl_iconGravity_new, Gravity.TOP);
        mIconWidth = ta.getDimension(R.styleable.BaseTabLayout_tl_iconWidth, dp2px(0));
        mIconHeight = ta.getDimension(R.styleable.BaseTabLayout_tl_iconHeight, dp2px(0));
        mIconMargin = ta.getDimension(R.styleable.BaseTabLayout_tl_iconMargin, dp2px(2.5f));

        mTabSpaceEqual = ta.getBoolean(R.styleable.BaseTabLayout_tl_tab_space_equal, true);
        mTabWidth = ta.getDimension(R.styleable.BaseTabLayout_tl_tab_width, dp2px(-1));
        mTabPadding = ta.getDimension(R.styleable.BaseTabLayout_tl_tab_padding,
                mTabSpaceEqual || mTabWidth > 0 ? dp2px(0) : dp2px(10));

        ta.recycle();
    }

//    public void setShouldExpand(boolean shouldExpand) {
//        this.shouldExpand = shouldExpand;
//        notifyDataSetChanged();
//    }

    /**
     * 更新数据
     */
    public abstract void notifyDataSetChanged();


    /**
     * 创建并添加tab
     */
    protected abstract void addTab(final int position, View tabView);

    public abstract void updateTabStyles();


    protected abstract void updateTabSelection(int position);

    private void calcOffset() {
        if (checkIndexOutOfBounds()) {
            return;
        }
        final View currentTabView = mTabsContainer.getChildAt(this.mCurrentTab);
        if (currentTabView != null) {
            mCurrentP.left = currentTabView.getLeft();
            mCurrentP.right = currentTabView.getRight();
        }

        final View lastTabView = mTabsContainer.getChildAt(this.mLastTab);
        if (lastTabView != null) {
            mLastP.left = lastTabView.getLeft();
            mLastP.right = lastTabView.getRight();
        }
        if (mLastP.left == mCurrentP.left && mLastP.right == mCurrentP.right) {
            invalidate();
        } else {
            mValueAnimator.setObjectValues(mLastP, mCurrentP);
            if (mIndicatorBounceEnable) {
                mValueAnimator.setInterpolator(mInterpolator);
            }

            if (mIndicatorAnimDuration < 0) {
                mIndicatorAnimDuration = mIndicatorBounceEnable ? 500 : 250;
            }
            mValueAnimator.setDuration(mIndicatorAnimDuration);
            mValueAnimator.start();
        }
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    protected int scrollOffset;
    protected int lastScrollX;

    protected void scrollToChild(int position, int offset) {

        if (mTabCount == 0) {
            return;
        }
        if (mTabsContainer.getChildAt(position) == null) {
            return;
        }

        int newScrollX = mTabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }
    }

    protected int currentPosition;
    protected float currentPositionOffset;
    protected float lastPositionOffset;
    protected boolean isLeftSwipe;
    protected boolean isReset = true;

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currentPosition = position;
        currentPositionOffset = positionOffset;
        lastPositionOffset = currentPositionOffset;
        LogUtils.logd(TAG,
                "BaseTabLayout onDraw currentPositionOffset : " + currentPositionOffset);
        if (isReset && currentPositionOffset < 0.5f) {
            isReset = false;
            isLeftSwipe = true;
            LogUtils.logd(TAG, "BaseTabLayout onDraw isLeftSwipe "
                    + isLeftSwipe);
        } else if (isReset && currentPositionOffset > 0.5f) {
            isReset = false;
            isLeftSwipe = false;
            LogUtils.logd(TAG, "BaseTabLayout onDraw isLeftSwipe  "
                    + isLeftSwipe);
        }
        if (currentPositionOffset == 0.0f) {
            isReset = true;
        }
        if (mTabsContainer.getChildAt(position) != null) {
            scrollToChild(position,
                    (int) (positionOffset * mTabsContainer.getChildAt(position).getWidth()));
            invalidate();
        }
        if (positionOffset > 0) {
            if (!isLeftSwipe) {
                onScrollProgress(position, 1 - positionOffset, isLeftSwipe);
            } else {
                onScrollProgress(position, positionOffset, isLeftSwipe);
            }
        }
    }

    private void calcIndicatorRect() {
        if (mIndicatorAnimStyle == INDICATOR_DAMPING && mIndicatorDamping) {
            LogUtils.logd(TAG,
                    "BaseTabLayout onDraw ----------------------------------Damping " + mIndicatorDamping);
            calcIndicatorRectStyleDamping();
        } else {
            calcIndicatorRectStyleNormal();
            LogUtils.logd(TAG,
                    "BaseTabLayout onDraw ----------------------------------Normal " + mIndicatorDamping);
        }

    }

    private void calcIndicatorRectStyleDamping() {
        if (checkIndexOutOfBounds()) {
            return;
        }
        View currentTabView = mTabsContainer.getChildAt(currentPosition);
        float lineLeft = currentTabView.getLeft(), lineRight = currentTabView.getRight();
        if (mIndicatorWidth > 0) {
            lineLeft = lineLeft + currentTabView.getWidth() / 2 - mIndicatorWidth / 2;
            lineRight = lineLeft + mIndicatorWidth;
        }
        if (currentPositionOffset > 0f && currentPosition < mTabCount - 1) {

            View nextTab = mTabsContainer.getChildAt(currentPosition + 1);

            float nextTabLeft = nextTab.getLeft();
            float nextTabRight = nextTab.getRight();
            if (mIndicatorWidth > 0) {
                nextTabLeft = nextTabLeft + nextTab.getWidth() / 2 - mIndicatorWidth / 2;
                nextTabRight = nextTabLeft + mIndicatorWidth;
            }
            lineLeft = currentPositionOffset <= 0.5 ? lineLeft :
                    ((2 * currentPositionOffset - 1) * nextTabLeft
                            + 2 * (1f - currentPositionOffset) * lineLeft);
            lineRight = currentPositionOffset <= 0.5 ? (2 * currentPositionOffset * nextTabRight
                    + (1f - 2 * currentPositionOffset) * lineRight) : nextTabRight;
        }
        mIndicatorRect.left = (int) lineLeft;
        mIndicatorRect.right = (int) lineRight;
    }

    private void calcIndicatorRectStyleNormal() {
        if (checkIndexOutOfBounds()) {
            return;
        }
        View currentTabView = mTabsContainer.getChildAt(mCurrentTab);
        float left = currentTabView.getLeft();
        float right = currentTabView.getRight();

        if (this.currentPosition < mTabCount - 1) {
            View nextTabView = mTabsContainer.getChildAt(this.currentPosition + 1);
            float nextTabLeft = nextTabView.getLeft();
            float nextTabRight = nextTabView.getRight();

            left = left + currentPositionOffset * (nextTabLeft - left);
            right = right + currentPositionOffset * (nextTabRight - right);
        }

        mIndicatorRect.left = (int) left;
        mIndicatorRect.right = (int) right;

        if (mIndicatorWidth < 0) {   //indicatorWidth小于0时,原jpardogo's PagerSlidingTabStrip

        } else {//indicatorWidth大于0时,圆角矩形以及三角形
            float indicatorLeft =
                    currentTabView.getLeft() + (currentTabView.getWidth() - mIndicatorWidth) / 2;

            mIndicatorRect.left = (int) indicatorLeft;
            mIndicatorRect.right = (int) (mIndicatorRect.left + mIndicatorWidth);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (checkIndexOutOfBounds()) {
            return;
        }
        View currentTabView = mTabsContainer.getChildAt(this.mCurrentTab);
        IndicatorPoint p = (IndicatorPoint) animation.getAnimatedValue();
        mIndicatorRect.left = (int) p.left;
        mIndicatorRect.right = (int) p.right;

        if (mIndicatorWidth < 0) {   //indicatorWidth小于0时,原jpardogo's PagerSlidingTabStrip

        } else {//indicatorWidth大于0时,圆角矩形以及三角形
            float indicatorLeft = p.left + (currentTabView.getWidth() - mIndicatorWidth) / 2;

            mIndicatorRect.left = (int) indicatorLeft;
            mIndicatorRect.right = (int) (mIndicatorRect.left + mIndicatorWidth);
        }
        invalidate();
    }

    /**
     * 检查当前tab索引是否超出边界
     *
     * @return
     */
    protected boolean checkIndexOutOfBounds() {
        return checkIndexOutOfBounds(this.mCurrentTab);
    }

    protected boolean checkIndexOutOfBounds(int tab) {
        if (tab < 0 || tab >= mTabsContainer.getChildCount()) {
            return true;
        }
        return false;
    }

    private boolean mIsInitCalcIndicator = true;

    public void setInitCalcIndicator(boolean initCalcIndicator) {
        mIsInitCalcIndicator = initCalcIndicator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || mTabCount <= 0) {
            return;
        }

        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        // draw divider
        if (mDividerWidth > 0) {
            mDividerPaint.setStrokeWidth(mDividerWidth);
            mDividerPaint.setColor(mDividerColor);
            for (int i = 0; i < mTabCount - 1; i++) {
                View tab = mTabsContainer.getChildAt(i);
                canvas.drawLine(paddingLeft + tab.getRight(), mDividerPadding,
                        paddingLeft + tab.getRight(),
                        height - mDividerPadding, mDividerPaint);
            }
        }

        // draw underline
        if (mUnderlineHeight > 0) {
            mRectPaint.setColor(mUnderlineColor);
            if (mUnderlineGravity == Gravity.BOTTOM) {
                canvas.drawRect(paddingLeft, height - mUnderlineHeight,
                        mTabsContainer.getWidth() + paddingLeft, height,
                        mRectPaint);
            } else {
                canvas.drawRect(paddingLeft, 0, mTabsContainer.getWidth() + paddingLeft,
                        mUnderlineHeight, mRectPaint);
            }
        }

        //draw indicator line
        if (mIndicatorAnimEnable) {
            if (mIsInitCalcIndicator) {
                mIsInitCalcIndicator = false;
                calcIndicatorRect();
            }
        } else {
            calcIndicatorRect();
        }


        if (mIndicatorStyle == STYLE_TRIANGLE) {
            if (mIndicatorHeight > 0) {
                mTrianglePaint.setColor(mIndicatorColor);
                mTrianglePath.reset();
                mTrianglePath.moveTo(paddingLeft + mIndicatorRect.left, height);
                mTrianglePath.lineTo(
                        paddingLeft + mIndicatorRect.left / 2 + mIndicatorRect.right / 2,
                        height - mIndicatorHeight);
                mTrianglePath.lineTo(paddingLeft + mIndicatorRect.right, height);
                mTrianglePath.close();
                canvas.drawPath(mTrianglePath, mTrianglePaint);
            }
        } else if (mIndicatorStyle == STYLE_BLOCK) {
            if (mIndicatorHeight < 0) {
                mIndicatorHeight = height - mIndicatorMarginTop - mIndicatorMarginBottom;
            } else {

            }

            if (mIndicatorHeight > 0) {
                if (mIndicatorCornerRadius < 0 || mIndicatorCornerRadius > mIndicatorHeight / 2) {
                    mIndicatorCornerRadius = mIndicatorHeight / 2;
                }

                mIndicatorDrawable.setColor(mIndicatorColor);
                mIndicatorDrawable.setBounds(
                        paddingLeft + (int) mIndicatorMarginLeft + mIndicatorRect.left,
                        (int) mIndicatorMarginTop,
                        (int) (paddingLeft + mIndicatorRect.right - mIndicatorMarginRight),
                        (int) (mIndicatorMarginTop + mIndicatorHeight));
                mIndicatorDrawable.setCornerRadius(mIndicatorCornerRadius);
                mIndicatorDrawable.draw(canvas);
            }
        } else {
               /* mRectPaint.setColor(mIndicatorColor);
                calcIndicatorRect();
                canvas.drawRect(getPaddingLeft() + mIndicatorRect.left, getHeight() -
                mIndicatorHeight,
                        mIndicatorRect.right + getPaddingLeft(), getHeight(), mRectPaint);*/

            if (mIndicatorHeight > 0) {
                mIndicatorDrawable.setColor(mIndicatorColor);
                if (mIndicatorGravity == Gravity.BOTTOM) {
                    mIndicatorDrawable.setBounds(
                            paddingLeft + (int) mIndicatorMarginLeft + mIndicatorRect.left,
                            height - (int) mIndicatorHeight - (int) mIndicatorMarginBottom,
                            paddingLeft + mIndicatorRect.right - (int) mIndicatorMarginRight,
                            height - (int) mIndicatorMarginBottom);
                } else {
                    mIndicatorDrawable.setBounds(
                            paddingLeft + (int) mIndicatorMarginLeft + mIndicatorRect.left,
                            (int) mIndicatorMarginTop,
                            paddingLeft + mIndicatorRect.right - (int) mIndicatorMarginRight,
                            (int) mIndicatorHeight + (int) mIndicatorMarginTop);
                }
                mIndicatorDrawable.setCornerRadius(mIndicatorCornerRadius);
                mIndicatorDrawable.draw(canvas);
            }
        }
    }

    //setter and getter
    public void setCurrentTab(int currentTab) {
        if (this.mCurrentTab > getTabCount() - 1) {
            this.mCurrentTab = 0;
        }
        mLastTab = this.mCurrentTab;
        this.mCurrentTab = currentTab;
        updateTabSelection(currentTab);

        if (mIndicatorAnimEnable && !mIndicatorDamping) {
            calcOffset();
        } else {
            invalidate();
        }
    }

    public void select(int selection) {
        if (checkIndexOutOfBounds(selection)) {
            return;
        }
        mIndicatorAnimStyle = INDICATOR_NORMAL;
        mCurrentTab = selection;
        int newScrollX = mTabsContainer.getChildAt(selection).getLeft();

        newScrollX -= scrollOffset;

        if (newScrollX != lastScrollX && checkedMoveLeft) {
            lastScrollX = newScrollX;
            smoothScrollTo(newScrollX, 0);
        }

        updateTabStyles();
        invalidate();
    }

    public void setIndicatorStyle(int indicatorStyle) {
        this.mIndicatorStyle = indicatorStyle;
        invalidate();
    }

    public void setCheckedMoveLeft(boolean checkedMoveLeft) {
        this.checkedMoveLeft = checkedMoveLeft;
    }

    public void setTabPadding(float tabPadding) {
        this.mTabPadding = dp2px(tabPadding);
        updateTabStyles();
    }

    public void setTabSpaceEqual(boolean tabSpaceEqual) {
        this.mTabSpaceEqual = tabSpaceEqual;
        updateTabStyles();
    }

    public void setTabWidth(float tabWidth) {
        this.mTabWidth = dp2px(tabWidth);
        updateTabStyles();
    }

    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorHeight(float indicatorHeight) {
        this.mIndicatorHeight = dp2px(indicatorHeight);
        invalidate();
    }

    public void setIndicatorHeight(int pxHeight) {
        this.mIndicatorHeight = pxHeight;
        invalidate();
    }

    public void setIndicatorWidth(float indicatorWidth) {
        this.mIndicatorWidth = dp2px(indicatorWidth);
        invalidate();
    }

    public void setIndicatorWidth(int pxWidth) {
        this.mIndicatorWidth = pxWidth;
        invalidate();
    }

    public void setIndicatorCornerRadius(float indicatorCornerRadius) {
        this.mIndicatorCornerRadius = dp2px(indicatorCornerRadius);
        invalidate();
    }

    public void setIndicatorGravity(int indicatorGravity) {
        this.mIndicatorGravity = indicatorGravity;
        invalidate();
    }

    public void setIndicatorMargin(float indicatorMarginLeft, float indicatorMarginTop,
                                   float indicatorMarginRight, float indicatorMarginBottom) {
        this.mIndicatorMarginLeft = dp2px(indicatorMarginLeft);
        this.mIndicatorMarginTop = dp2px(indicatorMarginTop);
        this.mIndicatorMarginRight = dp2px(indicatorMarginRight);
        this.mIndicatorMarginBottom = dp2px(indicatorMarginBottom);
        invalidate();
    }

    public void setIndicatorAnimDuration(long indicatorAnimDuration) {
        this.mIndicatorAnimDuration = indicatorAnimDuration;
    }

    public void setIndicatorAnimEnable(boolean indicatorAnimEnable) {
        this.mIndicatorAnimEnable = indicatorAnimEnable;
    }

    public void setIndicatorBounceEnable(boolean indicatorBounceEnable) {
        this.mIndicatorBounceEnable = indicatorBounceEnable;
    }

    public void setUnderlineColor(int underlineColor) {
        this.mUnderlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineHeight(float underlineHeight) {
        this.mUnderlineHeight = dp2px(underlineHeight);
        invalidate();
    }

    public void setUnderlineGravity(int underlineGravity) {
        this.mUnderlineGravity = underlineGravity;
        invalidate();
    }

    public void setDividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int res) {
        this.mDividerColor = mContext.getResources().getColor(res);
        invalidate();
    }

    public void setDividerWidth(float dpWidth) {
        this.mDividerWidth = dp2px(dpWidth);
        invalidate();
    }

    public void setDividerWidth(int pxWidth) {
        this.mDividerWidth = pxWidth;
        invalidate();
    }

    public void setDividerPadding(float dpPadding) {
        this.mDividerPadding = dp2px(dpPadding);
        invalidate();
    }

    public void setDividerPadding(int pxPadding) {
        this.mDividerPadding = pxPadding;
        invalidate();
    }

    public void setTextSize(float dpSize) {
        this.mTextsize = sp2px(dpSize);
        updateTabStyles();
    }

    public void setTextSize(int pxSize) {
        this.mTextsize = pxSize;
        updateTabStyles();
    }

    public void setTextSelectColor(int textSelectColor) {
        this.mTextSelectColor = textSelectColor;
        updateTabStyles();
    }

    public void setTextUnselectColor(int textUnselectColor) {
        this.mTextUnselectColor = textUnselectColor;
        updateTabStyles();
    }

    public void setTextBold(boolean textBold) {
        this.mTextBold = textBold;
        updateTabStyles();
    }

    public void setIconVisible(boolean iconVisible) {
        this.mIconVisible = iconVisible;
        updateTabStyles();
    }

    public void setIconGravity(int iconGravity) {
        this.mIconGravity = iconGravity;
        notifyDataSetChanged();
    }

    public void setIconWidth(float iconWidth) {
        this.mIconWidth = dp2px(iconWidth);
        updateTabStyles();
    }

    public void setIconHeight(float iconHeight) {
        this.mIconHeight = dp2px(iconHeight);
        updateTabStyles();
    }

    public void setIconMargin(float iconMargin) {
        this.mIconMargin = dp2px(iconMargin);
        updateTabStyles();
    }

    public void setTextAllCaps(boolean textAllCaps) {
        this.mTextAllCaps = textAllCaps;
        updateTabStyles();
    }

    public void setTabBackgroundResId(int tabBackgroundResId) {
        mTabBackgroundResId = tabBackgroundResId;
    }

    public int getTabCount() {
        return mTabCount;
    }

    public int getCurrentTab() {
        return mCurrentTab;
    }

    public int getIndicatorStyle() {
        return mIndicatorStyle;
    }

    public float getTabPadding() {
        return mTabPadding;
    }

    public boolean isTabSpaceEqual() {
        return mTabSpaceEqual;
    }

    public float getTabWidth() {
        return mTabWidth;
    }

    public int getIndicatorColor() {
        return mIndicatorColor;
    }

    public float getIndicatorHeight() {
        return mIndicatorHeight;
    }

    public float getIndicatorWidth() {
        return mIndicatorWidth;
    }

    public float getIndicatorCornerRadius() {
        return mIndicatorCornerRadius;
    }

    public float getIndicatorMarginLeft() {
        return mIndicatorMarginLeft;
    }

    public float getIndicatorMarginTop() {
        return mIndicatorMarginTop;
    }

    public float getIndicatorMarginRight() {
        return mIndicatorMarginRight;
    }

    public float getIndicatorMarginBottom() {
        return mIndicatorMarginBottom;
    }

    public long getIndicatorAnimDuration() {
        return mIndicatorAnimDuration;
    }

    public boolean isIndicatorAnimEnable() {
        return mIndicatorAnimEnable;
    }

    public boolean isIndicatorBounceEnable() {
        return mIndicatorBounceEnable;
    }

    public int getUnderlineColor() {
        return mUnderlineColor;
    }

    public float getUnderlineHeight() {
        return mUnderlineHeight;
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public float getDividerWidth() {
        return mDividerWidth;
    }

    public float getDividerPadding() {
        return mDividerPadding;
    }

    public float getTextsize() {
        return mTextsize;
    }

    public int getTextSelectColor() {
        return mTextSelectColor;
    }

    public int getTextUnselectColor() {
        return mTextUnselectColor;
    }

    public boolean isTextBold() {
        return mTextBold;
    }

    public boolean isTextAllCaps() {
        return mTextAllCaps;
    }

    public int getIconGravity() {
        return mIconGravity;
    }

    public float getIconWidth() {
        return mIconWidth;
    }

    public float getIconHeight() {
        return mIconHeight;
    }

    public float getIconMargin() {
        return mIconMargin;
    }

    public boolean isIconVisible() {
        return mIconVisible;
    }

    public void setIndicatorDamping(boolean indicatorDamping) {
        mIndicatorDamping = indicatorDamping;
    }

    public boolean isIndicatorDamping() {
        return mIndicatorDamping;
    }

    public View getTabView(int tab) {
        return mTabsContainer.getChildAt(tab);
    }

    protected OnTabSelectListener mListener;

    public void setOnTabSelectListener(OnTabSelectListener listener) {
        this.mListener = listener;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("mCurrentTab", mCurrentTab);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mCurrentTab = bundle.getInt("mCurrentTab");
            currentPosition = mCurrentTab;
            state = bundle.getParcelable("instanceState");
            if (mCurrentTab != 0 && mTabsContainer.getChildCount() > 0) {
                updateTabSelection(mCurrentTab);
            }
        }
        super.onRestoreInstanceState(state);
    }

    class IndicatorPoint {
        public float left;
        public float right;
    }

    private IndicatorPoint mCurrentP = new IndicatorPoint();
    private IndicatorPoint mLastP = new IndicatorPoint();

    class PointEvaluator implements TypeEvaluator<IndicatorPoint> {
        @Override
        public IndicatorPoint evaluate(float fraction, IndicatorPoint startValue,
                                       IndicatorPoint endValue) {
            float left = startValue.left + fraction * (endValue.left - startValue.left);
            float right = startValue.right + fraction * (endValue.right - startValue.right);
            IndicatorPoint point = new IndicatorPoint();
            point.left = left;
            point.right = right;
            return point;
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    TextView currentTitle;
    TextView nextTitle;
    int tempCurrentTab, tempNextTab;

    public void onScrollProgress(int position, float progress, boolean isLeftSwipe) {
        LogUtils.logd(TAG,
                "BaseTabLayout onDraw progress " + progress);
        int nextTab, currentTab;
        if (isLeftSwipe) {
            nextTab = position + 1;
            currentTab = position;
        } else {
            nextTab = position;
            currentTab = position + 1;
        }
        if (tempCurrentTab != currentTab || currentTitle == null) {
            currentTitle = getTitleView(currentTab);
            tempCurrentTab = currentTab;
        }
        if (tempNextTab != nextTab || nextTitle == null) {
            nextTitle = getTitleView(nextTab);
            tempNextTab = nextTab;
        }
        int r1 = 0, g1 = 0, b1 = 0, r2 = 0, g2 = 0, b2 = 0;
        if (currentTitle != null || nextTitle != null) {
            r1 = Color.red(mTextSelectColor);
            g1 = Color.green(mTextSelectColor);
            b1 = Color.blue(mTextSelectColor);
            r2 = Color.red(mTextUnselectColor);
            g2 = Color.green(mTextUnselectColor);
            b2 = Color.blue(mTextUnselectColor);
        }
        if (currentTitle != null) {
            int c1 = (int) (r1 - r1 * progress + (r2 * progress));
            int c2 = (int) (g1 - g1 * progress + (g2 * progress));
            int c3 = (int) (b1 - b1 * progress + (b2 * progress));
            currentTitle.setTextColor(Color.argb(255, c1, c2, c3));
        }
        if (nextTitle != null) {
            int c4 = (int) (r2 - r2 * progress + (r1 * progress));
            int c5 = (int) (g2 - g2 * progress + (g1 * progress));
            int c6 = (int) (b2 - b2 * progress + (b1 * progress));
            nextTitle.setTextColor(Color.argb(255, c4, c5, c6));
        }
    }

    protected abstract TextView getTitleView(int currentTab);
}
