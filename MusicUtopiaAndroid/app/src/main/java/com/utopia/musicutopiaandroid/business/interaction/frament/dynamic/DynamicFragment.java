package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.business.interaction.adapter.DynamicAdapter;
import com.utopia.musicutopiaandroid.business.interaction.adapter.ItemType;
import com.utopia.musicutopiaandroid.business.interaction.bean.DynamicBean;
import com.utopia.musicutopiaandroid.framework.base.fragment.BaseFragment;
import com.utopia.musicutopiaandroid.framework.comm.util.ThreadUtil;

import java.util.ArrayList;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
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
    private DynamicAdapter mAdapter; //数据适配器


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
        BGANormalRefreshViewHolder moocStyleRefreshViewHolder = new BGANormalRefreshViewHolder(mApp, true);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        mContain.setLayoutManager(new LinearLayoutManager(mApp, LinearLayoutManager.VERTICAL, false));

        //添加适配器的类型
        mAdapter = new DynamicAdapter(null);
        mAdapter.setEmptyView(true, true, View.inflate(getContext(), R.layout.text, null));
        mContain.setAdapter(mAdapter);
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endLoadingMore();
                ArrayList<DynamicBean> data = new ArrayList<>();
                data.add(new DynamicBean("name:1", ItemType.type_text));
                data.add(new DynamicBean("name:2", ItemType.type_tex_img));
//                data.add(new DynamicBean("name:3", ItemType.type_tex_img9));
                data.add(new DynamicBean("name:1", ItemType.type_text));
                data.add(new DynamicBean("name:2", ItemType.type_tex_img));
//                data.add(new DynamicBean("name:3", ItemType.type_tex_img9));
                mAdapter.addData(data);

            }
        },1000);

    }

    //下拉刷新回调
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ArrayList<DynamicBean> data = new ArrayList<>();
        data.add(new DynamicBean("name:1", ItemType.type_text));
        data.add(new DynamicBean("name:2", ItemType.type_tex_img));
        data.add(new DynamicBean("name:3", ItemType.type_tex_img9));
        data.add(new DynamicBean("name:1", ItemType.type_text));
        data.add(new DynamicBean("name:2", ItemType.type_tex_img));
//        data.add(new DynamicBean("name:3", ItemType.type_tex_img9));
        mAdapter.setNewData(data);
        //回调完成
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();


            }
        },5000);

    }

    //上拉加载更多 回调
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Log.e(TAG, "onBGARefreshLayoutBeginLoadingMore: " );

        //可以上拉返回 true 否则返
        // 回false
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endLoadingMore();
                ArrayList<DynamicBean> data = new ArrayList<>();
                data.add(new DynamicBean("name:1", ItemType.type_text));
                data.add(new DynamicBean("name:2", ItemType.type_tex_img));
//        data.add(new DynamicBean("name:3", ItemType.type_tex_img9));
                data.add(new DynamicBean("name:1", ItemType.type_text));
                data.add(new DynamicBean("name:2", ItemType.type_tex_img));
//        data.add(new DynamicBean("name:3", ItemType.type_tex_img9));
                mAdapter.addData(data);

            }
        },1000);


        return true;
    }


}
