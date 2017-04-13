package com.utopia.musicutopiaandroid.framework.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.utopia.musicutopiaandroid.R;

/**
 * 作者:Created by 简玉锋 on 2017/4/13 13:37
 * 邮箱: jianyufeng@38.hn
 * 1. 添加公共头部
 */

public abstract class SuperAppCompatActivity extends BaseAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    public void init()  {
        //获取布局视图
        int layoutId = getLayoutId();
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        // 所有activity的容器
        View mBaseLayoutView = mLayoutInflater.inflate(R.layout.super_activity_view,null);
        //所有视图的根视图
        LinearLayout mRootView = (LinearLayout) mBaseLayoutView.findViewById(R.id.base_root_view);
        // 添加标题
        if(getTitleLayout() != -1) {
          View mTopBarView = mLayoutInflater.inflate(getTitleLayout() , null);
            Toolbar toolbar = (Toolbar) mTopBarView.findViewById(R.id.toolbar);
            mRootView.addView(mTopBarView,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        // 添加布局 两种方式
        View  mContentView = getContentLayoutView();
        if(mContentView == null) {
            if (layoutId != -1) {
                mContentView = mLayoutInflater.inflate(getLayoutId(), null);
            }
        }
        mRootView.addView(mContentView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //设置布局
        setContentView(mBaseLayoutView);
    }

    protected abstract int getLayoutId();
    protected  View getContentLayoutView(){
        return null;
    };
    /**
     * 子类重载该方法自定义标题布局文件
     * @return
     */
    public int getTitleLayout() {
        return R.layout.super_activity_title_view;
    }
}
