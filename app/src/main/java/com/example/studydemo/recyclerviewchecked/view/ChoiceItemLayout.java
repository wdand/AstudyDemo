package com.example.studydemo.recyclerviewchecked.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.studydemo.R;

public class ChoiceItemLayout extends LinearLayout implements Checkable {

    private boolean mChecked;
    public ChoiceItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        setBackgroundResource(checked? R.color.colorAccent : android.R.color.transparent);
    }

    @Override
    public boolean isChecked() {
        return true;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
