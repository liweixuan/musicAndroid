package com.utopia.musicutopiaandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 17:07
 * 邮箱: jianyufeng@38.hn
 */
public class InteractionFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.refresh_ly)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.lv)
    ListView lv;

    public static InteractionFragment getInstance(String title) {

        InteractionFragment sInstance = new InteractionFragment();
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interaction, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAMeiTuanRefreshViewHolder refreshViewHolder = new BGAMeiTuanRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        // 设置下拉刷新控件的背景 drawable 资源 id
        refreshViewHolder.setRefreshViewBackgroundDrawableRes(R.drawable.bga_refresh_loding);
//        refreshViewHolder.setRefreshLayout(mRefreshLayout);
        // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("加载更多...");
        // 设置整个加载更多控件的背景颜色资源 id
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.colorAccent);
        // 设置整个加载更多控件的背景 drawable 资源 id
        refreshViewHolder.setLoadMoreBackgroundDrawableRes(R.mipmap.ic_launcher);
        // 设置正在加载更多时不显示加载更多控件
//        mRefreshLayout.setIsShowLoadingMoreView(false);

        // 设置下拉刷新控件的背景颜色资源 id
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.colorPrimaryDark);
        refreshViewHolder.setPullDownImageResource(R.drawable.bga_refresh_loding);
        refreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_loding);
        refreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_loding);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START



        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//            mRefreshLayout.setCustomHeaderView(mBanner, false);
        // 可选配置  -------------END

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        // 在这里加载最新数据
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mRefreshLayout.endRefreshing();
                    Toast.makeText(getContext(), "加载完成", Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    // 加载完毕后在 UI 线程结束加载更多
//                    mRefreshLayout.endLoadingMore();
                    Toast.makeText(getContext(), "加载完成", Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return true;
    }
}
