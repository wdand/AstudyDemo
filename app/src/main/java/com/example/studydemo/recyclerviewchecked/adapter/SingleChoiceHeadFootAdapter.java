package com.example.studydemo.recyclerviewchecked.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studydemo.R;
import com.example.studydemo.recyclerviewchecked.bean.Person;

import java.util.List;

public class SingleChoiceHeadFootAdapter extends RecyclerView.Adapter {

    private View headView;
    private View footView;
    public static final int HEAD = 1;
    public static final int NORMAL = 2;
    public static final int FOOT = 3;
    public List<Person> list;

    public SingleChoiceHeadFootAdapter(List<Person> list) {
        this.list = list;
    }


    public void addHeadView(View headView) {
        this.headView = headView;
    }

    public void addFootView(View footView) {
        this.footView = footView;
    }

    public int getHeadViewCount() {
        return headView == null ? 0 : 1;
    }

    public int getFootViewCount() {
        return footView == null ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            return new MyHolder(headView);
        }
        if (viewType == FOOT) {
            return new MyHolder(footView);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_choice, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == HEAD) {
            return;
        }
        if (getItemViewType(position) == FOOT) {
            return;
        }

        MyHolder holder = (MyHolder) viewHolder;
        Person person = list.get(position - getHeadViewCount());

        holder.rb.setChecked(person.isChecked());
        holder.tv1.setText("姓名："+person.getName());
        holder.tv2.setText("年龄："+person.getAge());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + getHeadViewCount() + getFootViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeadViewCount()) {
            return HEAD;
        }
        if (position >= list.size() + getHeadViewCount()) {
            return FOOT;
        }
        return NORMAL;
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        public RadioButton rb;
        public TextView tv1;
        public TextView tv2;

        public MyHolder(View itemView) {
            super(itemView);
            if (itemView == headView) {
                return;
            }
            if (itemView == footView) {
                return;
            }
            rb = (RadioButton) itemView.findViewById(R.id.rb);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
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
