package com.example.studydemo.recyclerviewchecked.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studydemo.R;
import com.example.studydemo.recyclerviewchecked.bean.Person;
import com.example.studydemo.recyclerviewchecked.view.ChoiceItemLayout;
import com.example.studydemo.recycleviewdemo.DataBean;
import com.example.studydemo.recycleviewdemo.SwipeMenuLayout;

import java.util.List;

public class BgSingleChoiceRecyAdapter extends RecyclerView.Adapter {

    public List<Person> list;

    public BgSingleChoiceRecyAdapter(List<Person> list) {
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bg_choice, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final MyHolder holder = (MyHolder) viewHolder;
        Person person = list.get(position);

        holder.tv1.setText("姓名：" + person.getName());
        holder.tv2.setText("年龄：" + person.getAge());

        SwipeMenuLayout layout = (SwipeMenuLayout) holder.itemView;
        layout.setChecked(person.isChecked());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }

        holder.view_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DataBean data = list.get(position);
//                list.remove(position);
//                list.add(0, data);
                notifyDataSetChanged();
            }
        });
        holder.view_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.swipeMenuLayout.collapseSmooth();
                Toast.makeText(holder.itemView.getContext(), "编辑", Toast.LENGTH_SHORT).show();
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
        holder.tv3.setOnClickListener(new View.OnClickListener() {
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
        public TextView tv3;
        TextView view_del;
        TextView view_edit;
        TextView tv_top;
        LinearLayout linearLayout;
        public SwipeMenuLayout swipeMenuLayout;

        public MyHolder (View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.parent_test);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            tv3 = (TextView) itemView.findViewById(R.id.tv3);
            tv_top = (TextView) itemView.findViewById(R.id.item_tv_top_choose);
            view_del = (TextView) itemView.findViewById(R.id.item_tv_del_choose);
            view_edit = (TextView) itemView.findViewById(R.id.item_tv_edit_choose);
            swipeMenuLayout = (SwipeMenuLayout) itemView.findViewById(R.id.item_layout_swip_choose);
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
