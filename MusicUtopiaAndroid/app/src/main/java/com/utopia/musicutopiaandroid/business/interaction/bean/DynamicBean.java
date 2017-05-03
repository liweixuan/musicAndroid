package com.utopia.musicutopiaandroid.business.interaction.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 作者:Created by 简玉锋 on 2017/5/2 10:08
 * 邮箱: jianyufeng@38.hn
 */

public class DynamicBean extends MultiItemEntity {
    public DynamicBean(String name,int type) {
        this.name = name;
        this.itemType = type;
    }
    private String name;
}
