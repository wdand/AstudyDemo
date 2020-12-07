package com.bingkong.bkbase.base;
import com.bingkong.bkbase.R;
import com.bingkong.bkbase.flux.actions.ActionsCreator;
import com.bingkong.bkbase.flux.stores.Store;
import com.bingkong.bkbase.ui.view.CustomLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:xianglei
 * Date: 2019/4/24 2:54 PM
 * Description:
 */
public abstract class RootActivity<STORE extends Store, CREATER extends ActionsCreator>
        extends BaseFluxActivity<STORE, CREATER> {

    protected SmartRefreshLayout mSmartRefresh;
    protected int mPage = 1;
    protected int mPerPage = 10;
    protected BaseAdapter mAdapter;
    protected RecyclerView mBaseRecycler;
    protected CustomLinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onViewCreated() {
        initSmartRefresh();
        mBaseRecycler = (RecyclerView) findViewById(R.id.base_recycler);
        if (mBaseRecycler != null) initRecyclerView();
    }

    private void initSmartRefresh() {
        mSmartRefresh = findViewById(R.id.smart_refresh);
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
        mPage = haveNextPage ? ++mPage : 0;
        mSmartRefresh.setEnableLoadMore(haveNextPage);
        finishLoading();
    }
}
