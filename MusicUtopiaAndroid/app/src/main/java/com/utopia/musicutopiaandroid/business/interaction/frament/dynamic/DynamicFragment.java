package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter.ComunAdapter;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter.Dra;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter.NormalRecyclerViewAdapter;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter.RefreshModel;
import com.utopia.musicutopiaandroid.framework.base.fragment.BaseFragment;
import com.utopia.musicutopiaandroid.framework.comm.util.ThreadUtil;
import com.utopia.musicutopiaandroid.framework.comm.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.utopia.musicutopiaandroid.R.id.refreshLayout;

/**
 * Created by Administrator on 2017/4/18/018.
 */

public class DynamicFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(refreshLayout)
     BGARefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
     RecyclerView mContain;
    private NormalRecyclerViewAdapter mAdapter;
    private ComunAdapter mAdapter1;
    private Dra mAdapter2;

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
        mAdapter1 = new ComunAdapter(getDates());
//        mAdapter.setOnRVItemClickListener(this);
//        mAdapter.setOnRVItemLongClickListener(this);
//        mAdapter.setOnItemChildClickListener(this);
//        mAdapter.setOnItemChildLongClickListener(this);


    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.ic_book_white_24dp);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.blue_btn_bg_color);
        moocStyleRefreshViewHolder.setLoadingMoreText("你说sfd");
        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.colorPrimary);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        mContain.setLayoutManager(new GridLayoutManager(mApp, 2, GridLayoutManager.VERTICAL, false));
//        mDataRv.setLayoutManager(new LinearLayoutManager(mApp, LinearLayoutManager.VERTICAL, false));
//        mContain.setAdapter(mAdapter1);
        mAdapter1.setDuration(1500);
        mAdapter1.setEmptyView(true,true,View.inflate(getContext(),R.layout.text,null));
        mAdapter1.openLoadMore(true);
        mAdapter1.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mAdapter1.addHeaderView(View.inflate(getContext(),R.layout.text1,null));
        mAdapter1.addHeaderView(View.inflate(getContext(),R.layout.text3,null));
        mAdapter1.addFooterView(View.inflate(getContext(),R.layout.text3,null));
        mAdapter1.addFooterView(View.inflate(getContext(),R.layout.text,null));
        mAdapter1.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ToastUtil.show("1111111111111111");
            }
        });

        mAdapter1.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ToastUtil.show(":::"+i);
            }
        });
        OnItemDragListener onItemDragListener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos){}
            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {}
            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {}
        };

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {}
            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}
            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {}

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {

            }
        };
        mAdapter2 = new Dra(getDates());
        mContain.setAdapter(mAdapter2);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter2);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mContain);
        // 开启拖拽
        mAdapter2.enableDragItem(itemTouchHelper, R.id.tv1, true);
        mAdapter2.setOnItemDragListener(onItemDragListener);


// 开启滑动删除
//        mAdapter2.enableSwipeItem();
//        mAdapter2.setOnItemSwipeListener(onItemSwipeListener);
    }


    List<RefreshModel>  getDates(){
        List<RefreshModel> s = new ArrayList<>() ;
        for (int i = 0; i < 4; i++) {
            s.add(new RefreshModel("item"+i,"夏:::"+i,i));
        }
        return s;
    };




    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                mAdapter1.setNewData(getDates());
//                mContain.smoothScrollToPosition(0);
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
                mAdapter1.clear();
            }
        }, 3000);


        return true;
    }






}
