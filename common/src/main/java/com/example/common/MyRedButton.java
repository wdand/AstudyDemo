package com.example.common;


//自定义控件：定义一个红色的圆，中间是数字20，每点击一次，数字减一，直到为零。

/*步骤：
1,创建一个MyRedButton继承自View
2，重写构造函数（三个）,创建init方法，在init里创建对象，初始化对象，不可以draw方法中创建对象，因为onDraw方法会被频繁调用
3，重写onDraw方法，在里面绘画，不要忘了invalidate（）
//自定义控件属性，可以改变圆的颜色，文本的颜色。
1，创建自定义属性的xml资源文件
2，获取对应属性，赋给onDraw作为绘制的参数


*/

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyRedButton extends View {
    public Paint paint=null;
    private int number;
    private String numberText;
    private Rect rect;
    private int backgroundColor;

    //    在第一和第二个构造函数中都时候了this，所以无论用哪个构造函数都会调用到init（）
    public MyRedButton(Context context) {//在new的时候会调用
        this(context,null);//用this代替super，将会调用第二个构造函数
    }

    public MyRedButton(Context context, @Nullable AttributeSet attrs) {//在xml的时候会调用，AttributeSet指MyRedButton在布局文件属性集
        this(context, attrs,0);//用this代替super，将会调用第三个构造函数
    }

    public MyRedButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        paint =new Paint();
        number=20;
        rect=new Rect();
//获取R.styleable.MyRedButton的自定义属性集合
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRedButton);
//        获取布局文件中设定的背景颜色
        backgroundColor=typedArray.getColor(R.styleable.MyRedButton_backgroundColorlor,Color.BLUE);
//   每点击一次，数字减一，直到为零。
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number>0){
                    number--;
                }
                invalidate();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=getWidth()/2;//getWidth()获得的是布局文件中制定控件的宽度，如在布局文件的MyRedButton控件的 android:layout_width="200dp"
        int y=getHeight()/2;//同理
        paint.setColor(backgroundColor);

//        画圆
        canvas.drawCircle(x,y,getWidth()/2,paint);//xy为圆心坐标
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        numberText=String.valueOf(number);
        paint.getTextBounds(numberText,0,numberText.length(),rect);//获取能包裹文本的rect，
//start表示开始度量的第一个字符串的下标，end表示比字符串中要度量的最后一个字符多1，所以不是numberText.length()-1
        int textWidth=rect.width();//为了让文本显示在中心，要将文本的绘制x坐标位置设为为圆心x坐标位置-包裹文本的rect的宽/2
        int textHeight=rect.height();//为了让文本显示在中心，要将文本的绘制基线位置设为为圆心坐标位置-+包裹文本的rect的高/2
        canvas.drawText(numberText,x-textWidth/2,y+textHeight/2,paint);//x为文本远点x坐标，y为文本基线位置
        invalidate();
    }

}

