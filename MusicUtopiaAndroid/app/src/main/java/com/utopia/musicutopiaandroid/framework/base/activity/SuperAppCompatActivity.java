package com.utopia.musicutopiaandroid.framework.base.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.framework.comm.util.ToastUtil;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 作者:Created by 简玉锋 on 2017/4/13 13:37
 * 邮箱: jianyufeng@38.hn
 * 1. 添加公共头部
 */

public abstract class SuperAppCompatActivity extends BaseAppCompatActivity implements View.OnClickListener {
    private SweetAlertDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();//设置布局
        ButterKnife.bind(this);
        initConfig();
        initView(savedInstanceState);//初始化控件
        setListener();//设置监听
        processLogic(savedInstanceState);//状态恢复逻辑处理
    }
    //从layout中加载布局
    protected abstract @LayoutRes int getLayoutId();
    //从创建的视图中加载布局
    protected  View getContentLayoutView(){
        return null;
    };
    /**
     * 子类重载该方法自定义标题布局文件
     * 如果不需要头部设置为 返回 -1 即可
     * @return
     */
    public int getTitleLayout() {
        return R.layout.super_activity_title_view;
    }
    //设置布局 添加头部视图
    public void initLayout()  {
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




    /**
     * 初始化
     */
    protected abstract void initConfig();
    /**
     * 初始化View控件 及视图容器
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 需要处理点击事件时，重写该方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
    }


    /**
     * 查找View
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    //显示Toast 提示
    protected void showToast(String text) {
        ToastUtil.show(text);
    }


    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(ActivityCompat.getColor(mApp,R.color.colorPrimary));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText("数据加载中...");
        }
        mLoadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}
