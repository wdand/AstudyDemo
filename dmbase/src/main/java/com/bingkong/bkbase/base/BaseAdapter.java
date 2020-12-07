package com.bingkong.bkbase.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Author:xianglei
 * Date: 2019/4/24 10:16 AM
 * Description:RecyclerView的Adapter
 */
public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private static final String TAG="BaseAdapter";
    public BaseAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<T>());
    }

    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public void refresh(List<T> list) {
        if (list != null && list.size() > 0) {
            setList(list);
        }
    }

    public void addOrRefreshList(List<T> list, int page) {
        if (page == 1) {
            refresh(list);
        } else {
            addData(list);
        }
    }

    public void remove() {
        int totalSize=getDefItemCount();
        if(totalSize>0) {
            removeAt(totalSize - 1);
        }
    }


    /**
     * same as recyclerView.setAdapter(), and save the instance of recyclerView
     */
    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() != null) {
            throw new RuntimeException("Don't bind twice");
        }
        setRecyclerView(recyclerView);
        getRecyclerView().setAdapter(this);
    }

}
