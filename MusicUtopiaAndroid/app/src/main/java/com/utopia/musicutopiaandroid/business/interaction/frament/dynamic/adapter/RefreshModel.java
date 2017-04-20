package com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/4/19/019.
 */

public class RefreshModel extends MultiItemEntity{
    public String title;
    public String detail;


    public RefreshModel() {
    }

    public RefreshModel(String title, String detail,int type) {
        this.title = title;
        this.detail = detail;
        this.itemType = type;
    }


}