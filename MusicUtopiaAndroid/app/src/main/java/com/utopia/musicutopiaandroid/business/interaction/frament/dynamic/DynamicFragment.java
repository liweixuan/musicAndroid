package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.framework.base.fragment.BaseFragment;
import com.utopia.musicutopiaandroid.framework.comm.util.ToastUtil;
import com.utopia.musicutopiaandroid.threeframe.adapter.AbsAdapter;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.utopia.musicutopiaandroid.R.id.refreshLayout;

/**
 * Created by Administrator on 2017/4/18/018.
 * <p>
 * compile 'com.zhy:okhttputils:2.6.2'
 * <p>
 * http://blog.csdn.net/fancylovejava/article/details/45787729/
 * https://github.com/wasabeef/glide-transformations
 */

public class DynamicFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(refreshLayout)
    BGARefreshLayout mRefreshLayout;//刷新上拉 容器

    @BindView(R.id.recycler_view)
    RecyclerView mContain;      //数据容器
    private AbsAdapter mAdapter; //数据适配器


    @Override
    protected int getLayoutId() {  //加载布局
        return R.layout.dynamic_fragment;
    }

    @Override
    protected void initConfig() {  //设置布局之前 可以做一些配置

    }

    @Override
    protected void initView(Bundle savedInstanceState) {  // 设置布局后做一些 视图控件的初始化

    }

    @Override
    protected void setListener() {  //设置控件的监听事件    在 initView 之后调用
        //设置刷新和上拉监听
        mRefreshLayout.setDelegate(this);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {   // 流程逻辑 处理
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.ic_book_white_24dp);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.blue_btn_bg_color);
        moocStyleRefreshViewHolder.setLoadingMoreText("你说sfd");
        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.colorPrimary);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        mContain.setLayoutManager(new GridLayoutManager(mApp, 2, GridLayoutManager.VERTICAL, false));
//        mDataRv.setLayoutManager(new LinearLayoutManager(mApp, LinearLayoutManager.VERTICAL, false));
//        mContain.setAdapter(mAdapter1);
        mAdapter.setDuration(1500);
        mAdapter.setEmptyView(true, true, View.inflate(getContext(), R.layout.text, null));
        mAdapter.openLoadMore(true);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mAdapter.addHeaderView(View.inflate(getContext(), R.layout.text1, null));
        mAdapter.addHeaderView(View.inflate(getContext(), R.layout.text3, null));
        mAdapter.addFooterView(View.inflate(getContext(), R.layout.text3, null));
        mAdapter.addFooterView(View.inflate(getContext(), R.layout.text, null));
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ToastUtil.show("1111111111111111");
            }
        });

    }

    //下拉刷新回调
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        //回调完成
        mRefreshLayout.endRefreshing();

    }

    //上拉加载更多 回调
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        //回调完成
        mRefreshLayout.endLoadingMore();

        //可以上拉返回 true 否则返回false
        return true;
    }


}
