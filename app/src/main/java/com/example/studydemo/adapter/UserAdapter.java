package com.example.studydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studydemo.R;
import com.example.studydemo.datas.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<User> list ;
    //布局加载器：将xml转为View对象RelativeLayout
    private LayoutInflater mInflater;
    public UserAdapter(Context context , List<User> list){
        this.list = list;
        //初始化布局加载器
        mInflater = LayoutInflater.from(context);
    }
    //配置显示的总item数量
    @Override
    public int getCount() {
        return list == null?0:list.size();
    }
    //按照位置获取数据对象
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    //根据位置获取item的id
    @Override
    public long getItemId(int position) {
        return position ;
    }
    //每个item的显示效果
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载每个item的布局对象（将item_user_layout转为RelativeLayout对象）
        View layout = mInflater.inflate(R.layout.item_user_layout,null);
        //初始化布局中的元素
        ImageView ivIcon = layout.findViewById(R.id.item_icon);
        TextView tvNick = layout.findViewById(R.id.item_nick);
        TextView tvSign = layout.findViewById(R.id.item_sign);
        //获取数据显示内容(数据绑定，将数据显示到布局中)
        User u = list.get(position);
        ivIcon.setImageResource(u.getIcon());
        tvNick.setText(u.getNick());
        tvSign.setText(u.getSign());

        return layout;
    }
}

