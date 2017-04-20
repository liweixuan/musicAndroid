package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.utopia.musicutopiaandroid.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21/021.
 * C:\Users\Administrator\.android\build-cache\b1d30ae326faffdf88985353052e41125d6a280b\output\jars\classes.jar!\com\chad\library\adapter\base\BaseQuickAdapter.class
 */

public class Dra extends BaseItemDraggableAdapter {


    public Dra( List data) {
        super(R.layout.text, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {

    }
}
