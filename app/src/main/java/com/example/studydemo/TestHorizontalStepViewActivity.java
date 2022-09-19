package com.example.studydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.studydemo.weight.HorizontalStepView;
import com.example.studydemo.weight.MyVerticalStepView;
import com.example.studydemo.weight.StepBean;
import com.example.studydemo.weight.StepModel;
import com.example.studydemo.weight.VerticalStepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class TestHorizontalStepViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_horizontal_step_view);
        MyVerticalStepView mStepView =findViewById(R.id.mystepview);
        List<StepModel> datas=new ArrayList<>();
        StepModel step1=new StepModel("您已提交订单，等待系统确认",StepModel.STATE_COMPLETED);
        StepModel step2=new StepModel("订单已确认并打包，预计12月16日送达",StepModel.STATE_COMPLETED);
        StepModel step3=new StepModel("包裹正在路上",StepModel.STATE_COMPLETED);
        StepModel step4=new StepModel("包裹正在派送",StepModel.STATE_PROCESSING);
        StepModel step5=new StepModel("感谢光临涂涂女装（店铺号85833577）",StepModel.STATE_PROCESSING);
        StepModel step6=new StepModel("感谢光临涂涂女装",StepModel.STATE_DEFAULT);
        datas.add(step1);
        datas.add(step2);
        datas.add(step3);
        datas.add(step4);
        datas.add(step5);
        datas.add(step6);
        mStepView.setmDatas(datas);


//        mStepView.setStepsViewIndicatorComplectingPosition(stepsBeanList.size() - 2)//设置完成的步数
//                .reverseDraw(false)//default is true
//                .setTextSize(14)
//                .setStepViewTexts(stepsBeanList)//总步骤
//                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
//                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
//                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsView text完成线的颜色
//                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
//                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
//                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
//                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
//

    }
}
