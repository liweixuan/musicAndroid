package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter;

import android.support.v7.widget.RecyclerView;

import com.utopia.musicutopiaandroid.R;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * Created by Administrator on 2017/4/18/018.
 */
public class NormalRecyclerViewAdapter extends BGARecyclerViewAdapter<RefreshModel> {

    public NormalRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.dynamic_item_normal);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, RefreshModel model) {

//        helper.setText(R.id.tv_item_normal_title, model.title)
//                .setText(R.id.tv_item_normal_detail, model.detail);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        helper.setItemChildClickListener(R.id.tv_item_normal_delete);
        helper.setItemChildLongClickListener(R.id.tv_item_normal_delete);

    }

}