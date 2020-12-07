package com.bingkong.bkbase.ui.widget.tablayout;

import android.content.Context;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bingkong.bkbase.R;


import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;

public class CommonTabLayout extends BaseTabLayout {
    protected ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public CommonTabLayout(Context context) {
        this(context, null, 0);
    }

    public CommonTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addNewTab(CustomTabEntity tabEntity) {
        if (tabEntity == null) {
            throw new IllegalStateException("TabEntitys can not be NULL or EMPTY !");
        }

        if (this.mTabEntities != null) {
            this.mTabEntities.add(tabEntity);
            notifyDataSetChanged();
        }
    }

    public void addNewTab(final String title) {

        if (this.mTabEntities != null) {
            this.mTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return title;
                }

                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });
            notifyDataSetChanged();
        }
    }

    /**
     * 创建并添加tab
     */
    @Override
    protected void addTab(final int position, View tabView) {
        TextView tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
        tv_tab_title.setText(mTabEntities.get(position).getTabTitle());
        if (mIconVisible) {
            ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
            iv_tab_icon.setImageResource(mTabEntities.get(position).getTabUnselectedIcon());
            if (mTabEntities.get(position).getTabUnselectedIcon() == 0 || mTabEntities.get(
                    position).getTabSelectedIcon() == 0) {
                iv_tab_icon.setVisibility(GONE);
            } else {
                iv_tab_icon.setVisibility(VISIBLE);
            }
        }
        tabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIndexOutOfBounds()) {
                    return;
                }
                boolean isDamping = isIndicatorDamping();
                setIndicatorDamping(false);
                int position = (Integer) v.getTag();
                if (mCurrentTab != position) {
                    setCurrentTab(position);
                    if (mListener != null) {
                        mListener.onTabSelect(position);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onTabReselect(position);
                    }
                }
                setIndicatorDamping(isDamping);
            }
        });

        /** 每一个Tab的布局参数 */
        LinearLayout.LayoutParams lp_tab = mTabSpaceEqual ?
                new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f) :
                new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        if (mTabWidth > 0) {
            lp_tab = new LinearLayout.LayoutParams((int) mTabWidth, FrameLayout.LayoutParams.MATCH_PARENT);
        }
        mTabsContainer.addView(tabView, position, lp_tab);
    }

    @Override
    public void updateTabStyles() {
        for (int i = 0; i < mTabCount; i++) {
            View tabView = mTabsContainer.getChildAt(i);
            tabView.setBackgroundColor(mTabBackgroundResId);
            tabView.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
            TextView tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            tv_tab_title.setTextColor(i == mCurrentTab ? mTextSelectColor : mTextUnselectColor);
            tv_tab_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextsize);
//            tv_tab_title.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
            if (mTextAllCaps) {
                tv_tab_title.setText(tv_tab_title.getText().toString().toUpperCase());
            }

            if (mTextBold) {
                tv_tab_title.getPaint().setFakeBoldText(mTextBold);
            }

            ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
            if (mIconVisible) {
                iv_tab_icon.setVisibility(VISIBLE);
                CustomTabEntity tabEntity = mTabEntities.get(i);
                if (tabEntity.getTabSelectedIcon() == 0 || tabEntity.getTabUnselectedIcon() == 0) {
                    iv_tab_icon.setVisibility(GONE);
                } else {
                    iv_tab_icon.setImageResource(
                            i == mCurrentTab ? tabEntity.getTabSelectedIcon()
                                    : tabEntity.getTabUnselectedIcon());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            mIconWidth <= 0 ? LinearLayout.LayoutParams.WRAP_CONTENT
                                    : (int) mIconWidth,
                            mIconHeight <= 0 ? LinearLayout.LayoutParams.WRAP_CONTENT
                                    : (int) mIconHeight);
                    if (mIconGravity == Gravity.LEFT) {
                        lp.rightMargin = (int) mIconMargin;
                    } else if (mIconGravity == Gravity.RIGHT) {
                        lp.leftMargin = (int) mIconMargin;
                    } else if (mIconGravity == Gravity.BOTTOM) {
                        lp.topMargin = (int) mIconMargin;
                    } else {
                        lp.bottomMargin = (int) mIconMargin;
                    }
                    iv_tab_icon.setLayoutParams(lp);
                }
            } else {
                iv_tab_icon.setVisibility(GONE);
            }
        }
    }

    @Override
    public void updateTabSelection(int position) {
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = mTabsContainer.getChildAt(i);
            final boolean isSelect = i == position;
            TextView tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            tab_title.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
            ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
            CustomTabEntity tabEntity = mTabEntities.get(i);
            int resourceId = isSelect ? tabEntity.getTabSelectedIcon()
                    : tabEntity.getTabUnselectedIcon();
            if (resourceId != -1) {
                iv_tab_icon.setImageResource(resourceId);
            }
        }
    }

    public void updateTabText(int position, String text) {
        if (mTabsContainer == null) {
            return;
        }

        if (position > mTabCount - 1 || position < 0) {
            return;
        }

        View tabView = mTabsContainer.getChildAt(position);
        if (tabView != null) {
            TextView tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            if (tv_tab_title != null) {
                tv_tab_title.setText(text);
            }
        }
    }

    public void deleteTab(int position) {
        if (position < 0) {
            return;
        }
        if (this.mTabEntities != null) {
            if (position <= mTabEntities.size() - 1) {
                mTabEntities.remove(position);
                notifyDataSetChanged();
            }
        }
    }

    public void setTabData(ArrayList<CustomTabEntity> tabEntitys) {
        if (tabEntitys == null || tabEntitys.isEmpty()) {
            return;
        }

        this.mTabEntities.clear();
        this.mTabEntities.addAll(tabEntitys);

        notifyDataSetChanged();
    }

    /**
     * 关联数据支持同时切换fragments
     */
//    public void setTabData(ArrayList<CustomTabEntity> tabEntitys, FragmentActivity fa,
//            int containerViewId,
//            ArrayList<Fragment> fragments) {
//        mFragmentChangeManager = new FragmentChangeManager(fa.getSupportFragmentManager(),
//                containerViewId, fragments);
//        setTabData(tabEntitys);
//    }


    // show MsgTipView
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private SparseArray<Boolean> mInitSetMap = new SparseArray<>();
//
//    /**
//     * 文案类型未读消息
//     * @param position
//     * @param text
//     */
//    public void showMsg(int position, String text){
//        if(TextUtils.isEmpty(text)) return;
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//        View tabView = mTabsContainer.getChildAt(position);
//        MsgView tipView = (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//        if (tipView != null) {
//            UnreadMsgUtils.show(tipView, text);
//            if (mInitSetMap.get(position) != null && mInitSetMap.get(position)) {
//                return;
//            }
//            //修改红色文案偏移位置
//            setMsgMargin(position, 0, ViewUtils.dp2px(PPContext.getAppContext(),3), 0, 0);
//
//            mInitSetMap.put(position, true);
//        }
//    }
//
//    private void setMsgMargin(int position, int left, int top, int right, int bottom) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//        View tabView = mTabsContainer.getChildAt(position);
//        MsgView tipView = (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//        if (tipView != null) {
//            MarginLayoutParams lp = (MarginLayoutParams) tipView.getLayoutParams();
//            if(left > 0) {
//                lp.leftMargin = left;
//            }
//            if(top > 0) {
//                lp.leftMargin = top;
//            }
//            if(right > 0) {
//                lp.leftMargin = right;
//            }
//            if(bottom > 0) {
//                lp.leftMargin = bottom;
//            }
//
//            tipView.setLayoutParams(lp);
//        }
//    }

//    /**
//     * 显示未读消息
//     *
//     * @param position 显示tab位置
//     * @param num      num小于等于0显示红点,num大于0显示数字
//     */
//    public void showMsg(int position, int num) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//
//        View tabView = mTabsContainer.getChildAt(position);
//        MsgView tipView = (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//        if (tipView != null) {
//            UnreadMsgUtils.show(tipView, num);
//
//            if (mInitSetMap.get(position) != null && mInitSetMap.get(position)) {
//                return;
//            }
//
//            if (!mIconVisible) {
//                setMsgMargin(position, 1, 4);
//            } else {
//                setMsgMargin(position, 0,
//                        mIconGravity == Gravity.LEFT || mIconGravity == Gravity.RIGHT ? 4 : 0);
//            }
//
//            mInitSetMap.put(position, true);
//        }
//    }

    /**
     * 更新数据
     */
    @Override
    public void notifyDataSetChanged() {
        mTabsContainer.removeAllViews();
        this.mTabCount = mTabEntities.size();
        View tabView;
        for (int i = 0; i < mTabCount; i++) {
            if (mIconGravity == Gravity.LEFT) {
                tabView = inflate(mContext, R.layout.mp_layout_tab_left, null);
            } else if (mIconGravity == Gravity.RIGHT) {
                tabView = inflate(mContext, R.layout.mp_layout_tab_right, null);
            } else if (mIconGravity == Gravity.BOTTOM) {
                tabView = inflate(mContext, R.layout.mp_layout_tab_bottom, null);
            } else {
                tabView = inflate(mContext, R.layout.mp_layout_tab_top, null);
            }

            tabView.setTag(i);
            addTab(i, tabView);
        }

        updateTabStyles();
    }

//    /**
//     * 显示未读红点
//     *
//     * @param position 显示tab位置
//     */
//    public void showDot(int position) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
////        showMsg(position, 0);
//    }

//    public void hideMsg(int position) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//
//        View tabView = mTabsContainer.getChildAt(position);
//        MsgView tipView = (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//        if (tipView != null) {
//            tipView.setVisibility(GONE);
//        }
//    }

//    public boolean isDotVisible(int position) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//
//        View tabView = mTabsContainer.getChildAt(position);
//        return ViewUtils.isVisible(tabView.findViewById(R.id.rtv_msg_tip));
//    }

//    /**
//     * 设置提示红点偏移,注意
//     * 1.控件为固定高度:参照点为tab内容的右上角
//     * 2.控件高度不固定(WRAP_CONTENT):参照点为tab内容的右上角,此时高度已是红点的最高显示范围,所以这时bottomPadding其实就是topPadding
//     */
//    public void setMsgMargin(int position, float leftPadding, float bottomPadding) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//        View tabView = mTabsContainer.getChildAt(position);
//        MsgView tipView = (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//        if (tipView != null) {
//            TextView tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
//            mTextPaint.setTextSize(mTextsize);
//            float textWidth = mTextPaint.measureText(tv_tab_title.getText().toString());
//            float textHeight = mTextPaint.descent() - mTextPaint.ascent();
//            MarginLayoutParams lp = (MarginLayoutParams) tipView.getLayoutParams();
//
//            float iconH = mIconHeight;
//            float margin = 0;
//            if (mIconVisible) {
//                if (iconH <= 0) {
//                    iconH = mContext.getResources().getDrawable(
//                            mTabEntities.get(position).getTabSelectedIcon()).getIntrinsicHeight();
//                }
//                margin = mIconMargin;
//            }
//
//            if (mIconGravity == Gravity.TOP || mIconGravity == Gravity.BOTTOM) {
//                lp.leftMargin = dp2px(leftPadding);
//                lp.topMargin =
//                        mHeight > 0 ? (int) (mHeight - textHeight - iconH - margin) / 2 - dp2px(
//                                bottomPadding) : dp2px(
//                                bottomPadding);
//            } else {
//                lp.leftMargin = dp2px(leftPadding);
//                lp.topMargin = mHeight > 0 ? (int) (mHeight - Math.max(textHeight, iconH)) / 2
//                        - dp2px(bottomPadding) : dp2px(
//                        bottomPadding);
//            }
//
//            tipView.setLayoutParams(lp);
//        }
//    }
//
//    /** 当前类只提供了少许设置未读消息属性的方法,可以通过该方法获取MsgView对象从而各种设置 */
//    public MsgView getMsgView(int position) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//        View tabView = mTabsContainer.getChildAt(position);
//        MsgView tipView = (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//        return tipView;
//    }

    public TextView getTitleView(int tab) {
        if (checkIndexOutOfBounds(tab)) {
            tab = 0;
        }
        View tabView = mTabsContainer.getChildAt(tab);
        if (tabView != null) {
            return (TextView) tabView.findViewById(R.id.tv_tab_title);
        }
        return null;
    }

    public void setViewPager(final ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                CommonTabLayout.this.onPageScrolled(i, v, i1);
            }

            @Override
            public void onPageSelected(int i) {
                CommonTabLayout.this.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        if (mListener == null) {
            this.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    viewPager.setCurrentItem(position, false);

                }

                @Override
                public void onTabReselect(int position) {

                }
            });
        }
    }
//
//    public MsgView getRedDot(int position) {
//        if (position >= mTabCount) {
//            position = mTabCount - 1;
//        }
//        View tabView = mTabsContainer.getChildAt(position);
//        return (MsgView) tabView.findViewById(R.id.rtv_msg_tip);
//    }
}
