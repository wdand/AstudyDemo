package com.example.studydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.studydemo.adapter.UserAdapter;
import com.example.studydemo.banner.BannerAct;
import com.example.studydemo.broadcastreceiver.MyStaticBroadcastReceiver;
import com.example.studydemo.datas.User;
import com.example.studydemo.recycleviewdemo.RecycleViewDeleteAct;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@Route(path = ArouterConstants.MAIN_ACT)
public class MainActivity extends Activity {
    ListView listView;
    DynamicReceiver dynamicReceiver = new DynamicReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction("DynamicReceiver");
        //注册广播接收
        registerReceiver(dynamicReceiver, filter);

        TextView textView = findViewById(R.id.save_data);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        //第二个参数为缺省值，如果不存在该key，返回缺省值
        String name = sp.getString("name", "111");
        textView.setText(name);
        List<User> list = new ArrayList<>();
        ARouter.init(this.getApplication());
        initData(list);
        listView = findViewById(R.id.main_listView);
        UserAdapter adapter = new UserAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);
        //为列表视图中选中的项添加响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();//获取选择项的值
                switch (position) {
                    case 0:
                        ARouter.getInstance().build(ArouterConstants.BOTTOM_NACIGATION_PARENT).
                                withString("buttonName", "string").
                                navigation();
                        break;
                    case 1:
                        EventBus.getDefault().post(new String("213"));
                        break;
                    case 2:
//                        ARouter.getInstance().build(ArouterConstants.RECYCLEVIEW_DETETEDEMO).
//                                navigation();
                        Intent intent = new Intent(MainActivity.this, RecycleViewDeleteAct.class);
                        startActivity(intent);
                        break;
                    case 3:
                        ARouter.getInstance().build(ArouterConstants.CUSTOMDIALOT_ACT).
                                navigation();
                        break;
                    case 4:
                        sendStatic();
                        break;
                    case 5:
                        Intent intents = new Intent();
                        intents.setAction("DynamicReceiver");
                        intents.putExtra("sele", "动态广播");
                        sendBroadcast(intents);
                        break;
                    case 6:
                        ARouter.getInstance().build(ArouterConstants.RECYCLEVIEW_CHECKED_PARENT).
                                navigation();
                        break;
                    case 7:
                        Intent intentss = new Intent(MainActivity.this, BannerAct.class);
                        startActivity(intentss);
                        break;
                    case 8:
                        ARouter.getInstance().build(ArouterConstants.MENU_ACT).navigation();
                        break;
                    case 9:
                        ARouter.getInstance().build(ArouterConstants.ADDRESS_SELECT_ACT).navigation();
                        break;
                    case 10:
                        daoxu("hello world");
                        break;
                    case 11:
                        saveData("weidong");
                        break;
                }
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }


        });

    }

    private void daoxu(String s) {
        char[] chars = s.toCharArray();
        String str = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            str = str + chars[i];
        }
        Log.e("daoxu", "daoxu: ................" + str);
    }

    private void initData(List<User> list) {
        list.add(new User(R.drawable.actor, "3种底部导航栏实现方式", ""));
        list.add(new User(R.drawable.actor, "EventBus未完成", ""));
        list.add(new User(R.drawable.actor, "带侧滑菜单的Recycleview", ""));
        list.add(new User(R.drawable.actor, "自定义Dialog", ""));
        list.add(new User(R.drawable.actor, "静态广播", ""));
        list.add(new User(R.drawable.actor, "动态广播", ""));
        list.add(new User(R.drawable.actor, "recycleView 单选、多选", ""));
        list.add(new User(R.drawable.actor, "轮播Banner", ""));
        list.add(new User(R.drawable.actor, "MenuActivity", ""));
        list.add(new User(R.drawable.actor, "地址选择Activity", ""));
        list.add(new User(R.drawable.actor, "daoxu", ""));
        list.add(new User(R.drawable.actor, "savaData", ""));

    }

    private void saveData(String info) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();   //获取编辑器
        editor.putString("name", info); //存入String型数据
        editor.putInt("age", 18);         //存入Int类型数据
        editor.commit();                //提交修改，否则不生效
    }

    //静态广播点击
    public void sendStatic() {
        Intent intent = new Intent(MainActivity.this,MyStaticBroadcastReceiver.class);//显示指定组件名
        intent.setAction("weidong");
        intent.putExtra("info", "panhouye");
        sendBroadcast(intent);
    }

    //通过继承 BroadcastReceiver建立动态广播接收器
    class DynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //通过土司验证接收到广播
            Toast t = Toast.makeText(context, "动态广播：" + intent.getStringExtra("sele"), Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 0);//方便录屏，将土司设置在屏幕顶端
            t.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销静态广播
        unregisterReceiver(dynamicReceiver);
    }
}
