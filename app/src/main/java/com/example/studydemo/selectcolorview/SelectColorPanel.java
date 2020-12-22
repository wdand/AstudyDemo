package com.example.studydemo.selectcolorview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bingkong.bkbase.constant.Const;
import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bkbase.utils.ScreenSizeUtils;
import com.bingkong.bkbase.utils.ViewUtils;
import com.example.studydemo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SelectColorPanel extends LinearLayout implements View.OnClickListener{
    private static final String TAG = SelectColorPanel.class.getSimpleName();

    private Context mContext;
    private View rootView;
    private LinearLayout container;
    private int isEdt = -1;
    private ArrayList<ProductColorData> mDataList;
    private LinkedList<ProductColorData> mRecentDataList = new LinkedList<>();//最近使用的素材，最多10个
    private int mRows = 1;
    private int itemWidth;
    private List<LinearLayout> mRowLayoutList = new ArrayList<>();
    private LinearLayout mRecentItemLayout;
    private static final int COLUMN = 10;
    private SelectColotCircleView mSelectedView;
    private ChangeListener mChangeListener;
    private ProductColorData currentData;
    private String currentColor = "";
    private ImageView mArrowView;
    private TextView mPanelNameTV;
    private int showMode = Const.SHOW_FULL;
    private static final int ITEM_MARGIN = 3;//一个item的左右margin 3dp
    private static final int ROW_LEFT_RIGHT_MARGIN = 13;//一行距离左右的边距
    private int index = -1;

    public SelectColorPanel(Context context) {
        super(context);
        init(context);
    }

    public SelectColorPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelectColorPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelectColorPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.select_color_panel, this, true);
        mPanelNameTV = rootView.findViewById(R.id.scolor_panel_name);
        mArrowView = rootView.findViewById(R.id.scolor_panel_arrow_button);
        mArrowView.setOnClickListener(this);
        container = rootView.findViewById(R.id.scolor_panel_body);
        mRecentItemLayout = rootView.findViewById(R.id.scolor_recent);
        initRecentLayout();
    }

    public void setDataList(ArrayList<ProductColorData> materialDataList) {
        mDataList = materialDataList;
        if(mDataList == null || mDataList.size() == 0)
            return;

        if(mDataList.size() % COLUMN == 0) {
            mRows = mDataList.size() / COLUMN;//10个一行
        } else {
            mRows = 1 + mDataList.size() / COLUMN;
        }
        LogUtils.logd(TAG, "setDataList, row num: " + mRows);

        for(int i = 0; i < mRows; i++) {
            mRowLayoutList.add(createRowLayout());
            container.addView(mRowLayoutList.get(i));
        }

        //计算单个item的大小
        int screenW = ScreenSizeUtils.getInstance(mContext).getScreenWidth();
        //一栏的左右边距16dp, 10个按钮，各自间距 2 * ITEM_MARGIN  = 6 dp，共9个内间距
        itemWidth = (int)((screenW - 2 * ViewUtils.dip2px(mContext, ROW_LEFT_RIGHT_MARGIN + ITEM_MARGIN)
                - 9 * ViewUtils.dip2px(mContext, ITEM_MARGIN * 2)) / 10.0f);
        LogUtils.logd(TAG, "setDataList, itemWidth: " + itemWidth + " screenW " + screenW);

        //创建单个view
        for(int j = 0; j < mRows; j++) {
            LinearLayout rowLayout = mRowLayoutList.get(j);
            int startIndex = j * COLUMN;
            int maxIndex = (j+1) * COLUMN < mDataList.size() ? (j+1) * COLUMN : mDataList.size();
            List<ProductColorData> tmpList = mDataList.subList(startIndex, maxIndex);
            for(int k = 0; k < tmpList.size(); k++) {
                rowLayout.addView(createView(tmpList.get(k)));
            }
        }

        if (index == -1 ){
            initDefaultItem(0);
        }else {
            initDefaultItem(index);
        }

    }

    /**
     * 设置第一个为默认选中
     */
    private void initDefaultItem(int colorIndex) {
        currentData = mDataList.get(colorIndex);
        adRecentData(currentData);

        if(mRowLayoutList.size() > 0) {
            View firstView = mRowLayoutList.get(colorIndex/10).getChildAt(colorIndex%10);
            if(firstView instanceof SelectColotCircleView) {
                ((SelectColotCircleView) firstView).setData(currentData);
                ((SelectColotCircleView) firstView).setSelected(true,mContext);
                mSelectedView = (SelectColotCircleView) firstView;
            }
        }
    }

    private void initRecentLayout() {
        int sideMargin = ViewUtils.dip2px(mContext, ROW_LEFT_RIGHT_MARGIN);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mRecentItemLayout.getLayoutParams();
        layoutParams.setMargins(sideMargin, layoutParams.topMargin, sideMargin, layoutParams.bottomMargin);
        mRecentItemLayout.setLayoutParams(layoutParams);
    }

    private SelectColotCircleView createView(ProductColorData data) {
        SelectColotCircleView circleView = new SelectColotCircleView(mContext);
        circleView.setData(data);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                itemWidth, itemWidth);
        int margin = ViewUtils.dip2px(mContext, ITEM_MARGIN);//左右margin
        layoutParams.setMargins(margin, 0, margin, 0);
        circleView.setLayoutParams(layoutParams);
        circleView.setOnClickListener(this);
        return circleView;
    }


    public void setShowMode(int mode) {
        if(mRowLayoutList == null || mRecentItemLayout == null)
            return;

        for(int i = 0; i < mRowLayoutList.size(); i++) {
            LinearLayout rowLayout = mRowLayoutList.get(i);
            rowLayout.setVisibility(mode == Const.SHOW_FULL ? VISIBLE : GONE);
        }
        mRecentItemLayout.setVisibility(mode == Const.SHOW_RECENT ? VISIBLE : GONE);
        if(mRecentItemLayout.getVisibility() == VISIBLE) {
            showRecentView();
        }
    }

    private void showRecentView() {
        LogUtils.logd(TAG, "showRecentView");
        mRecentItemLayout.removeAllViews();
        for(Iterator iter = mRecentDataList.iterator(); iter.hasNext();) {
            ProductColorData data = (ProductColorData) iter.next();
            mRecentItemLayout.addView(createView(data));
        }
    }

    public void setPanelName(String name) {
        mPanelNameTV.setText(name);
    }


    private LinearLayout createRowLayout() {
        LinearLayout layout = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int topDownMargin = ViewUtils.dip2px(mContext, 2);//行与行的距离2dp + 2dp
        int sideMargin = ViewUtils.dip2px(mContext, ROW_LEFT_RIGHT_MARGIN);//一行的左右边距13dp，加上icon本身margin 3， 刚好16
        layoutParams.setMargins(sideMargin, topDownMargin, sideMargin, topDownMargin);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        return layout;
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.material_panel_arrow_button) {
//            toggleShowMode();
//        } else
            if(view instanceof SelectColotCircleView) {
            if(mSelectedView != null) {
                if(mSelectedView == view)
                    return;
                mSelectedView.setSelected(false,mContext);
            }

            mSelectedView = (SelectColotCircleView) view;
            mSelectedView.setSelected(true,mContext);
            currentData = mSelectedView.getData();
            currentColor = mSelectedView.getColor();
            LogUtils.logd(TAG, "CircleView click, color is " + currentData.toString());
//            Toast.makeText(mContext, "We know you have selected a different color, and that it is not reflected in the thumbnail, but we promise that we'll ship the order with your selected color.", Toast.LENGTH_SHORT).show();
            if(mChangeListener != null) {
                mChangeListener.onDataChange(currentData);
                if(showMode == Const.SHOW_FULL) {
                    //记住最近颜色
                    adRecentData(currentData);
                }
            }
        }
    }
    public String getCurrentColor() {
        if(currentData==null) {
            return null;
        }
        return currentData.getColor();
    }
    public void setCurrentColorIndex(int index) {
        this.index = index;
    }

    private void adRecentData(ProductColorData materialData) {
        LogUtils.logd(TAG, "adRecentData " + materialData.toString());
        if(mRecentDataList.size() >= 10) {
            ProductColorData lastData = mRecentDataList.removeLast();
            LogUtils.logd(TAG, "adRecentData, size more than 10, will remove one " + lastData.toString());

        }
        ProductColorData recentData = materialData.clone(materialData);
        mRecentDataList.addFirst(recentData);
    }

//    private void toggleShowMode() {
//        showMode = showMode == Const.SHOW_FULL ? Const.SHOW_RECENT : Const.SHOW_FULL;
//        if(showMode == Const.SHOW_FULL) {
//            mArrowView.setImageResource(R.drawable.arrow_up_small);
//        } else {
//            mArrowView.setImageResource(R.drawable.arrow_down_small);
//        }
//        setShowMode(showMode);
//
//    }

    public void setChangeListener(ChangeListener listener) {
        mChangeListener = listener;
    }

    public interface ChangeListener {
        void onDataChange(ProductColorData materialData);
    }
}
