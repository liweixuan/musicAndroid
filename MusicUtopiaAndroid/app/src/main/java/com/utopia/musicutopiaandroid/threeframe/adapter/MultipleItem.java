package com.utopia.musicutopiaandroid.threeframe.adapter;

/**
 * Created by Administrator on 2017/4/21/021.
 */

public class MultipleItem {
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    private int layoutResId;

    public MultipleItem(int type, int layoutResId) {
        this.type = type;
        this.layoutResId = layoutResId;
    }
}
