package com.example.studydemo.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studydemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyVerticalStepView extends LinearLayout {
    private List<StepModel> mDatas = new ArrayList<>();//下面给出了它的set跟get方法
    private Context mContext;

    public MyVerticalStepView(Context context) {
        this(context, null);
    }

    public MyVerticalStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVerticalStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

    }

    private void init() {
        setOrientation(VERTICAL);
        mDatas = getmDatas();//获取数据
        for (int i = 0; i < mDatas.size(); i++) {
            //获取布局，注意第二个参数一定是ViewGroup，否则margin padding之类的属性将不能使用
            View itemview = LayoutInflater.from(mContext).inflate(R.layout.stepview_item, this, false);
            TextView description = (TextView) itemview.findViewById(R.id.description_tv);
            View line = itemview.findViewById(R.id.line_v);
            View line1 = itemview.findViewById(R.id.line_vx);
            ImageView icon = (ImageView) itemview.findViewById(R.id.stepicon_iv);
            description.setText(mDatas.get(i).getDescription());
            //根据不同状态设置不同图标
            switch (mDatas.get(i).getCurrentState()) {
                case StepModel.STATE_COMPLETED:
                    icon.setImageResource(R.drawable.complted);
                    line.setVisibility(VISIBLE);
                    line1.setVisibility(GONE);
                    break;
                case StepModel.STATE_DEFAULT:
                    //结尾图标隐藏竖线
                    line.setVisibility(GONE);
                    icon.setImageResource(R.drawable.default_icon);
                    break;
                case StepModel.STATE_PROCESSING:
                    line.setVisibility(GONE);
                    line1.setVisibility(VISIBLE);
                    icon.setImageResource(R.drawable.attention);
                    break;
            }

            this.addView(itemview);

        }
        requestLayout();//重新绘制布局
        invalidate();//刷新当前界面
    }

    public List<StepModel> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<StepModel> mDatas) {
        this.mDatas = mDatas;
        init();
    }
}
