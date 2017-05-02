package com.utopia.musicutopiaandroid.threeframe.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.utopia.musicutopiaandroid.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21/021.
 */

public abstract class AbsAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter {

    public AbsAdapter(List<T> data) {
        super(data);

    }
    //一种类型布局
    public AbsAdapter(List data, int layoutResId) {
        super(data);
        addItemType(0, R.layout.text);
    }
    //多种类型的布局
    public AbsAdapter(List data, MultipleItem... item) {
        super(data);
        for (MultipleItem i:item) {
            addItemType(i.getType(),i.getLayoutResId());
        }
    }
//    //设置数据
//    @Override
//    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {
//
//    }
//
    //清除所有数据
    public void clear() {
        mData.clear();
        this.notifyDataSetChanged();
    }

}
