package com.utopia.musicutopiaandroid.business.interaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.framework.base.fragment.BaseFragment;
import com.utopia.musicutopiaandroid.framework.comm.util.ThreadUtil;
import com.utopia.musicutopiaandroid.framework.comm.util.ToastUtil;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 17:07
 * 邮箱: jianyufeng@38.hn
 */
public class InteractionFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.refresh_ly)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.lv)
    ListView lv;

    @Override
    protected int getLayoutId() {
        return R.layout.interaction_fragment;
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
        mRefreshLayout.setRefreshScaleDelegate(new BGARefreshLayout.BGARefreshScaleDelegate() {
            @Override
            public void onRefreshScaleChanged(float scale, int moveYDistance) {
                Log.i(TAG, "scale:" + scale + " moveYDistance:" + moveYDistance);
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
//        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
//        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.ic_book_white_24dp);
//        moocStyleRefreshViewHolder.setUltimateColor(R.color.colorAccent);
//        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
//        moocStyleRefreshViewHolder.setSpringDistanceScale(0.5f);

//        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(mApp, true);
//        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
//        stickinessRefreshViewHolder.setRotateImage(R.mipmap.ic_launcher);
//        mRefreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);

        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(mApp, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.drawable.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mRefreshLayout.setRefreshViewHolder(meiTuanRefreshViewHolder);

    }

    @Override
    protected void onLazyLoadOnce() {
        super.onLazyLoadOnce();

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
           @Override
            public void run() {
               ToastUtil.show("刷新完毕");
                mRefreshLayout.endRefreshing();
            }
        },3000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {


        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show("加载完毕");
                mRefreshLayout.endLoadingMore();
            }
        },3000);
            return true;
    }
}
