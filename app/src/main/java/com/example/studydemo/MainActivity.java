package com.example.studydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = ArouterConstants.MAIN_ACT)
public class MainActivity extends Activity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        // 发布事件
        EventBus.getDefault().post(new MessageEvent(1,"Hello EventBus!"));

        ARouter.init(this.getApplication());
        listView = findViewById(R.id.main_listView);
        String data[] = {"3种底部导航栏实现方式", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
        //创建数组适配器，作为数据源和列表控件联系的桥梁
        //第一个参数：上下文环境
        //第二个参数：当前列表项加载的布局文件
        //第三个参数：数据源
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        //listview视图加载适配器
        listView.setAdapter(arrayAdapter);
        //为列表视图中选中的项添加响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();//获取选择项的值
                switch (position) {
                    case 0:
                        ARouter.getInstance().build(ArouterConstants.BOTTOM_NACIGATION_PARENT).
                                withString("buttonName","string").
                                navigation();
                        break;
                }
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
