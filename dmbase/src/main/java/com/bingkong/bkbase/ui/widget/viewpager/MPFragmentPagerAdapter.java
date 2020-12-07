package com.bingkong.bkbase.ui.widget.viewpager;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bingkong.bkbase.R;

import com.bingkong.bkbase.utils.LogUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;


public class MPFragmentPagerAdapter extends FragmentPagerAdapter implements ViewPagerSlidingTabStrip.ViewTabProvider {
    private static final String TAG = "MPFragmentPagerAdapter";
    private ArrayList<String> mTitles;
    private ArrayList<Integer> mIcons;
    private ArrayList<Integer> mSelectedIcons;
    private ArrayList<Fragment> mFragments;

    public HashMap<Integer, Integer> mIndexMaps; //cardType -> index

    private Context mContext;
    private SoftReference<TabSelectedListener> mTabSelectedListener;
    private FragmentManager mFragmentManager;
    private int mItemId = 10;

    public MPFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        mIcons = new ArrayList<>();
        mSelectedIcons = new ArrayList<>();
        mIndexMaps = new HashMap<>();
        mFragmentManager = fm;
    }

    public void add(int cardType, String title, Fragment fragment) {
        mIndexMaps.put(cardType, mFragments.size());
        mTitles.add(title);
        mFragments.add(fragment);
    }

    public int getIndex(int cardType) {
        int result = -1;
        Integer index = mIndexMaps.get(cardType);
        if (index != null) {
            result = index.intValue();
        }
        return result;
    }

    public int getCardTypeByIndex(int index) {
        Iterator iter = mIndexMaps.entrySet().iterator();
        //找出第一个cardType
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            int ii = (int) entry.getValue();

            if (index == ii) {
                return (int) key;
            }
        }
        return -1;
    }


    @Override
    public Fragment getItem(int i) {
        return i < mFragments.size() ? mFragments.get(i) : null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public long getItemId(int position) {
        if (isClear) {
            return mItemId++;
        } else {
            return super.getItemId(position);
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public View getPageView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mp_circle_fc_home_tab_item, null);
        TextView fc_tab_textview = (TextView) view.findViewById(R.id.fc_tab_textview);
        fc_tab_textview.setText(mTitles.get(position));
        return view;
    }

    @Override
    public int select(View view, int postion, boolean selected) {
        TextView fc_tab_textview = (TextView) view.findViewById(R.id.fc_tab_textview);
        LogUtils.logi(TAG, "select " + postion + " selected " + selected);
        if (selected) {
            fc_tab_textview.setTextColor(mContext.getResources().getColor(R.color.black));
            if (mTabSelectedListener != null && mTabSelectedListener.get() != null) {
                int cardType = -1;
                Iterator<Map.Entry<Integer, Integer>> entries = mIndexMaps.entrySet().iterator();
                Map.Entry<Integer, Integer> entry;

                while (entries.hasNext()) {
                    entry = entries.next();
                    if (entry.getValue().intValue() == postion) {
                        cardType = entry.getKey().intValue();
                        break;
                    }
                }

                if (cardType == -1) {
                    LogUtils.loge(TAG, "QZFragmentPagerAdapter::select can't find cardtype");
                }
                if (mTabSelectedListener.get() != null) {
                    mTabSelectedListener.get().onTabSelected(cardType);
                }
            }
        } else {
            fc_tab_textview.setTextColor(mContext.getResources().getColor(R.color.color_333333));
        }
        return 0;
    }

    @Override
    public void onTabItemClick(View item, int position) {

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    private boolean isClear;

    public void setClear(boolean clear) {
        isClear = clear;
    }

    public void clear() {
        isClear = true;
        try {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            // mDeleteCityArry是删除的Fragment的位置的数组
            for (int i = 0; i < mFragments.size(); i++) {
                transaction.remove(mFragments.get(i));
            }
            if (mFragments.size() > 0) {
                transaction.commit();
            }
            mFragmentManager.executePendingTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mFragments.clear();
        mIndexMaps.clear();
        mTitles.clear();
        mIcons.clear();
        mSelectedIcons.clear();

    }

    @Override
    public int getItemPosition(Object item) {
        int index = mFragments.indexOf(item);
        LogUtils.logd(TAG, "QZFragmentPagerAdapter getItemPosition index=" + index + " " + item + " clear="
                + isClear);
        if (isClear) {
            if (index == -1) {
                return POSITION_NONE;
            } else {
                return index;
            }
        } else {
            return super.getItemPosition(item);
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        try {
            super.finishUpdate(container);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通知QZFansCircleHomeActivity当前处于哪个tab
    public static interface TabSelectedListener {
        public void onTabSelected(int cardType);
    }
}
