package com.utopia.musicutopiaandroid.framework.base.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.utopia.musicutopiaandroid.R;

/**
 * 作者:Created by 简玉锋 on 2017/4/13 15:54
 * 邮箱: jianyufeng@38.hn
 */

public class TopBarView extends LinearLayout{
    public TopBarView(Context context) {
        this(context,null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
       LayoutInflater.from(getContext()).inflate(R.layout.top_bar_view, this, true);
    }
}
