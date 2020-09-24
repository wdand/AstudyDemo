package com.example.studydemo.recyclerviewchecked.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studydemo.R;
import com.example.studydemo.recyclerviewchecked.bean.Person;
import com.example.studydemo.recyclerviewchecked.view.ChoiceItemLayout;
import com.example.studydemo.recycleviewdemo.SwipeMenuLayout;

import java.util.List;

public class BgMultipleChoiceRecyAdapter extends RecyclerView.Adapter {

    public List<Person> list;

    public BgMultipleChoiceRecyAdapter(List<Person> list) {
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bg_choice, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        MyHolder holder = (MyHolder) viewHolder;
        Person person = list.get(position);
        SwipeMenuLayout layout = (SwipeMenuLayout) holder.itemView;
        layout.setChecked(person.isChecked());

        holder.tv1.setText("姓名：" + person.getName());
        holder.tv2.setText("年龄：" + person.getAge());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(v, position);
//            }
//        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv1;
        public TextView tv2;
        public LinearLayout linearLayout ;

        public MyHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.parent_test);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
