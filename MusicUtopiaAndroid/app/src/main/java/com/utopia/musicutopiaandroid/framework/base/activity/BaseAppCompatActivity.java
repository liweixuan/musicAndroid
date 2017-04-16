package com.utopia.musicutopiaandroid.framework.base.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.utopia.musicutopiaandroid.application.App;
import com.utopia.musicutopiaandroid.R;

/**
 * 作者:Created by 简玉锋 on 2017/4/13 11:51
 * 邮箱: jianyufeng@38.hn
 *  1 activity 的切换动画
 *  2
 */

public class BaseAppCompatActivity extends AppCompatActivity {
    protected String TAG;//标记
    protected App mApp;  //环境

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initBaseConfig();
    }
    //基类的一些 小配置
    private void initBaseConfig() {
        //标记
        TAG = this.getClass().getSimpleName();
        //环境
        mApp = App.getInstance();
        //通过判断当前sdk_int大于4.4(kitkat),则通过代码的形式设置status bar为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void startActivities(Intent[] intents) {
        super.startActivities(intents);
        onStartActivityAction();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivities(Intent[] intents, Bundle bundle) {
        super.startActivities(intents, bundle);
        onStartActivityAction();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartActivityAction();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void startActivity(Intent intent, Bundle bundle) {
        super.startActivity(intent, bundle);
        onStartActivityAction();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        onStartActivityAction();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        onStartActivityAction();
    }

    private void onStartActivityAction() {
        /***
         *给activity跳转加上动画效果
         */
        super.overridePendingTransition(WindowAnimation.activityOpenEnterAnimation, WindowAnimation.activityOpenExitAnimation);
    }
    @Override
    public void finish() {
        super.finish();
        super.overridePendingTransition(WindowAnimation.activityCloseEnterAnimation,WindowAnimation.activityCloseExitAnimation);
    }

    //Activity 切换的所有动画
    public static class WindowAnimation {
        public static int activityOpenEnterAnimation;
        public static int activityOpenExitAnimation;
        public static int activityCloseEnterAnimation;
        public static int activityCloseExitAnimation;
        static {
                activityOpenEnterAnimation = R.anim.slide_right_in;
                activityOpenExitAnimation  = R.anim.slide_left_out;
                activityCloseEnterAnimation = R.anim.slide_left_in;
                activityCloseExitAnimation = R.anim.slide_right_out;

        }
    }


}
