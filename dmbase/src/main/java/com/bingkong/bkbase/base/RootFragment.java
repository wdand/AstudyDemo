package com.bingkong.bkbase.base;

import android.os.Bundle;

import android.view.View;

import com.bingkong.bkbase.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.bingkong.bkbase.flux.actions.ActionsCreator;
import com.bingkong.bkbase.flux.stores.Store;
import com.bingkong.bkbase.ui.view.CustomLinearLayoutManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:xianglei
 * Date: 2019/4/24 2:32 PM
 * Description:
 */
public abstract class RootFragment<STORE extends Store, CREATER extends ActionsCreator>
        extends BaseFlux2Fragment<STORE, CREATER> {

    protected SmartRefreshLayout mSmartRefresh;
    protected int mPage = 1;
    protected int mPerPage = 10;
    protected BaseAdapter mAdapter;
    protected RecyclerView mBaseRecycler;
    protected CustomLinearLayoutManager mLinearLayoutManager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSmartRefresh();
        mBaseRecycler = (RecyclerView) mContentView.findViewById(R.id.base_recycler);
        if (mBaseRecycler != null) initRecyclerView();
    }

    private void initSmartRefresh() {
        mSmartRefresh = mContentView.findViewById(R.id.smart_refresh);
        if (mSmartRefresh != null) {
            mSmartRefresh.setEnableLoadMore(false);
            mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    onRefreshData(refreshlayout);
                }
            });
            mSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    onLoadMoreData(refreshLayout);
                }
            });
        }

    }

    protected void initRecyclerView() {
        mAdapter = getAdapter();
        mLinearLayoutManager = new CustomLinearLayoutManager(mContext);
        mBaseRecycler.setLayoutManager(mLinearLayoutManager);
        mAdapter.bindToRecyclerView(mBaseRecycler);
    }

    protected BaseAdapter getAdapter() {
        return null;
    }

    protected void onRefreshData(RefreshLayout refreshlayout) {
        mPage = 1;
        initRequest();
    }

    protected void onLoadMoreData(RefreshLayout refreshlayout) {
        initRequest();
    }

    protected void initRequest() {
    }

    public void finishLoading() {
        if (mSmartRefresh != null) {
            mSmartRefresh.finishRefresh(0);
            mSmartRefresh.finishLoadMore(200);
        }
    }

    protected void setNextPage(int total) {
        boolean haveNextPage = total > mPage * mPerPage;
        mPage = haveNextPage ? mPage++ : 0;
        mSmartRefresh.setEnableLoadMore(haveNextPage);
        finishLoading();
    }
}
