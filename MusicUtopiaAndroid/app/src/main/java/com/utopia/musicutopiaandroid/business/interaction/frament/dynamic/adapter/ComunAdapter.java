package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.utopia.musicutopiaandroid.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21/021.
 */

public class ComunAdapter extends BaseMultiItemQuickAdapter {

    public ComunAdapter(List data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.text);
        addItemType(MultipleItem.TEXT1, R.layout.text1);
        addItemType(MultipleItem.TEXT2, R.layout.text2);
        addItemType(MultipleItem.TEXT3, R.layout.text3);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {

    }
    public void clear(){
        mData.clear();
        this.notifyDataSetChanged();
    }

}
