package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter.NormalRecyclerViewAdapter;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter.RefreshModel;
import com.utopia.musicutopiaandroid.framework.base.fragment.BaseFragment;
import com.utopia.musicutopiaandroid.framework.comm.util.ThreadUtil;
import com.utopia.musicutopiaandroid.framework.comm.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildLongClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.utopia.musicutopiaandroid.R.id.refreshLayout;

/**
 * Created by Administrator on 2017/4/18/018.
 */

public class DynamicFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener, BGAOnRVItemLongClickListener, BGAOnItemChildClickListener, BGAOnItemChildLongClickListener {
    @BindView(refreshLayout)
     BGARefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
     RecyclerView mContain;
    private NormalRecyclerViewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.dynamic_fragment;
    }

    @Override
    protected void initConfig() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
        mAdapter = new NormalRecyclerViewAdapter(mContain);
        mAdapter.setOnRVItemClickListener(this);
        mAdapter.setOnRVItemLongClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);


    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.ic_book_white_24dp);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.blue_btn_bg_color);
        moocStyleRefreshViewHolder.setLoadingMoreText("你说sfd");
        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.colorPrimary);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        mContain.addItemDecoration(BGADivider.newShapeDivider());
        mContain.setLayoutManager(new GridLayoutManager(mApp, 2, GridLayoutManager.VERTICAL, false));
//        mDataRv.setLayoutManager(new LinearLayoutManager(mApp, LinearLayoutManager.VERTICAL, false));
        mContain.setAdapter(mAdapter);
    }

    List<RefreshModel>  getDates(){
        List<RefreshModel> s = new ArrayList<>() ;
        for (int i = 0; i < 5; i++) {
            s.add(new RefreshModel("item"+i,"夏:::"+i));
        }
        return s;
    };
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                mAdapter.setData(getDates());
                mContain.smoothScrollToPosition(0);
            }
        }, 4000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endLoadingMore();
                dismissLoadingDialog();
                mAdapter.addMoreData(getDates());
            }
        }, 3000);


        return true;
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        ToastUtil.show("onRVItemClick");
    }

    @Override
    public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
        ToastUtil.show("onRVItemLongClick");

        return false;
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        ToastUtil.show("onItemChildClick");

    }

    @Override
    public boolean onItemChildLongClick(ViewGroup parent, View childView, int position) {
        ToastUtil.show("onItemChildLongClick");

        return false;
    }
}
