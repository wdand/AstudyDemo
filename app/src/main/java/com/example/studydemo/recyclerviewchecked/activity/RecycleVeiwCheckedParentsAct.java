package com.example.studydemo.recyclerviewchecked.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.studydemo.ArouterConstants;
import com.example.studydemo.R;

@Route(path = ArouterConstants.RECYCLEVIEW_CHECKED_PARENT)
public class RecycleVeiwCheckedParentsAct extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_checked_parent);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(RecycleVeiwCheckedParentsAct.this, SingleChoiceActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(RecycleVeiwCheckedParentsAct.this, MultipleChoiceActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(RecycleVeiwCheckedParentsAct.this, BgSingleChoiceActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(RecycleVeiwCheckedParentsAct.this, BgMultipleChoiceActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(RecycleVeiwCheckedParentsAct.this, SingleChoiceHeadFootActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(RecycleVeiwCheckedParentsAct.this, MultipleChoiceHeadFootActivity.class));
                break;
            case R.id.btn7:
//                startActivity(new Intent(MainActivity.this, MultipleChoiceActivity.class));
                break;
        }
    }
}
