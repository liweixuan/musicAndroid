package com.utopia.musicutopiaandroid.business.interaction.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

/**
 * 作者:Created by 简玉锋 on 2017/5/2 10:08
 * 邮箱: jianyufeng@38.hn
 */

public class DynamicBean extends MultiItemEntity {
    public DynamicBean(String name,int type) {
        this.name = name;
        this.itemType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImgs() {
        imgs = new ArrayList<>();
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
//        imgs.add("123");
        imgs.add("123");
        return imgs;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }

    private String name;
    private ArrayList<String> imgs;
}
