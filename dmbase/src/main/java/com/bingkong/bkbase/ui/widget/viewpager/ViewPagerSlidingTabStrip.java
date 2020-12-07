package com.bingkong.bkbase.ui.widget.viewpager;

import android.content.Context;
import android.os.Build;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bingkong.bkbase.R;
import com.bingkong.bkbase.ui.widget.tablayout.BaseTabLayout;

import androidx.viewpager.widget.ViewPager;


public class ViewPagerSlidingTabStrip extends BaseTabLayout {
    private static final String TAG = ViewPagerSlidingTabStrip.class.getSimpleName();
    protected LinearLayout.LayoutParams defaultTabLayoutParams;
    protected LinearLayout.LayoutParams expandedTabLayoutParams;
    private ViewPager pager;
    private final PageListener pageListener = new PageListener(this);
    public ViewPager.OnPageChangeListener delegatePageListener;
    private int selectedPosition = 0;
    private int tabBackgroundResId;
    private boolean textAllCaps = true;
    private boolean checkedMoveLeft = true;
    private boolean shouldExpand = false;
    private boolean isSmooth = true;

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
        updateTabStyles();
    }

    public ViewPagerSlidingTabStrip(Context context) {
        this(context, null, 0);
    }

    public ViewPagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        expandedTabLayoutParams =
                new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f);
        defaultTabLayoutParams = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
    }

    public void setTextColor(int textColor) {
        mTextUnselectColor = textColor;
        updateTabStyles();
    }

    public void addTextTab(final int position, String title) {
        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine();
        addTab(position, tab);
    }

    public void select(int selection) {
        mIndicatorAnimStyle = INDICATOR_NORMAL;
        selectedPosition = selection;
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

    public void setSelectedTextColor(int color) {
        mTextSelectColor = color;
        updateTabStyles();
    }

    public interface ViewTabProvider {
        View getPageView(int position);

        int getCount();

        int select(View view, int postion, boolean selected);

        void onTabItemClick(View item, int position);
    }

    //     when no viewpager
    private ViewTabProvider viewTabProvider;

    public void setViewTabProvider(ViewTabProvider viewTabProvider) {
        this.viewTabProvider = viewTabProvider;
        if (pager == null) {
            for (int i = 0; i < viewTabProvider.getCount(); i++) {
                addTab(i, viewTabProvider.getPageView(i));
            }
        }
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        mTabPadding = paddingPx;
        updateTabStyles();
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        if (pager.getAdapter() instanceof ViewTabProvider) {
            setViewTabProvider((ViewTabProvider) pager.getAdapter());
        }

        pager.addOnPageChangeListener(pageListener);

        notifyDataSetChanged();
    }

    public void setTextColorResource(int res) {
        mTextUnselectColor = mContext.getResources().getColor(res);
        updateTabStyles();
    }

    public void setCheckedMoveLeft(boolean checkedMoveLeft) {
        this.checkedMoveLeft = checkedMoveLeft;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }

    public void setTextSize(int textSize) {
        super.setTextSize(textSize);
    }

    private class PageListener implements ViewPager.OnPageChangeListener {
        BaseTabLayout mBaseTabLayout;

        public PageListener(BaseTabLayout baseTabLayout) {
            mBaseTabLayout = baseTabLayout;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mBaseTabLayout.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }


        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                scrollToChild(pager.getCurrentItem(), 0);
            }

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            selectedPosition = position;
            if (mBaseTabLayout != null) {
                mCurrentTab = position;
            }
            updateTabStyles();
            if (delegatePageListener != null) {
                delegatePageListener.onPageSelected(position);
            }
        }
    }

    @Override
    public void updateTabSelection(int position) {
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = mTabsContainer.getChildAt(i);
            final boolean isSelect = i == position;
            if (tabView instanceof TextView) {
                TextView tv = (TextView) tabView;
                tv.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
            }
        }
    }

    @Override
    public void updateTabStyles() {
        int childCount = mTabsContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = mTabsContainer.getChildAt(i);
            v.setBackgroundResource(tabBackgroundResId);
            if (i == selectedPosition) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
            if (viewTabProvider != null) {
                if (i == selectedPosition) {
                    v.setSelected(true);
                    viewTabProvider.select(v, i, true);
                } else {
                    viewTabProvider.select(v, i, false);
                    v.setSelected(false);
                }
            }
            if (v instanceof TextView) {
                TextView tab = (TextView) v;
                tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextsize);
//                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                tab.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
                if (i == selectedPosition) {
                    tab.setTextColor(mTextSelectColor);
                } else {
                    tab.setTextColor(mTextUnselectColor);
                }
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        tab.setAllCaps(true);
                    } else {
                        tab.setText(tab.getText().toString().toUpperCase(locale));
                    }
                }
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        if (pager == null) {
            return;
        }
        mTabsContainer.removeAllViews();

        mTabCount = pager.getAdapter().getCount();

        for (int i = 0; i < mTabCount; i++) {

            if (pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
            }
            if (pager.getAdapter() instanceof ViewTabProvider) {
                addTab(i, ((ViewTabProvider) pager.getAdapter()).getPageView(i));
            } else {
                addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
            }
        }

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                currentPosition = pager.getCurrentItem();
                selectedPosition = currentPosition;
                updateTabStyles();
                scrollToChild(currentPosition, 0);
            }
        });
    }

    // 设置Tab是否自动填充满屏幕的
    public void setShouldExpand(boolean expand) {
        shouldExpand = expand;
        notifyDataSetChanged();
    }

    private void addIconTab(final int position, int resId) {

        ImageButton tab = new ImageButton(getContext());
        tab.setImageResource(resId);

        addTab(position, tab);
    }

    @Override
    public void addTab(final int position, final View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pager != null) {
                    if (isSmooth) {
                        pager.setCurrentItem(position);
                    } else {
                        pager.setCurrentItem(position, false);
                    }
                } else {
                    select(position);
                    if (viewTabProvider != null) {
                        viewTabProvider.onTabItemClick(tab, position);
                    }
                }
                if (mListener != null) {
                    mListener.onTabSelect(position);
                }
                setIndicatorDamping(false);
                setCurrentTab(position);
            }
        });
        mTabsContainer.addView(tab, position,
                shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);

        if (pager == null) {
            mTabCount = mTabsContainer.getChildCount();
        }
    }

    public interface IconTabProvider {
        int getPageIconResId(int position);
    }

    @Override
    protected TextView getTitleView(int currentTab) {
        if (currentTab >= 0 && currentTab < mTabCount) {
            View view = mTabsContainer.getChildAt(currentTab);
            if (view instanceof TextView) {
                return (TextView) view;
            } else if (view instanceof RelativeLayout) {
                return (TextView) view.findViewById(R.id.fc_tab_textview);
            }
        }
        return null;
    }
}
