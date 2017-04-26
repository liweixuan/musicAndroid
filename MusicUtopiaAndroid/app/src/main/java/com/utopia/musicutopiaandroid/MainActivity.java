package com.utopia.musicutopiaandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.utopia.musicutopiaandroid.business.friend.FriendFragment;
import com.utopia.musicutopiaandroid.business.interaction.InteractionFragment;
import com.utopia.musicutopiaandroid.business.message.MessageFragment;
import com.utopia.musicutopiaandroid.business.my.MyFragment;
import com.utopia.musicutopiaandroid.business.teach.TeachFragment;
import com.utopia.musicutopiaandroid.framework.base.activity.SuperAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 12:00
 * 邮箱: jianyufeng@38.hn
 * 主界面
 */

public class MainActivity extends SuperAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    //底部导航
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;


    FragmentManager fm =null;
    //选择的导航 默认是第一个
    int lastSelectedPosition = 0;

    //未读消息
    private BadgeItem numberBadgeItem;

    private List<Fragment> list;
//    private InteractionFragment interactionFragment;
//    private TeachFragment teachFragment;
//    private MessageFragment messageFragment;
//    private FriendFragment friendFragment;
//    private MyFragment myFragment;

    @Override
    protected void initConfig() {

         Class[] mFragments = new Class[]{
                InteractionFragment.class,
                TeachFragment.class,
                MessageFragment.class,
                FriendFragment.class,
                MyFragment.class,};
        list = new ArrayList<>();
        for (int i = 0; i < mFragments.length; i++) {
            list.add( Fragment.instantiate(this, mFragments[i].getName()));
        }
//        interactionFragment = (InteractionFragment) Fragment.instantiate(this, InteractionFragment.class.getName());
//        teachFragment = (TeachFragment) Fragment.instantiate(this, TeachFragment.class.getName());
//        messageFragment = (MessageFragment) Fragment.instantiate(this, MessageFragment.class.getName());
//        friendFragment = (FriendFragment) Fragment.instantiate(this, FriendFragment.class.getName());
//        myFragment = (MyFragment) Fragment.instantiate(this, MyFragment.class.getName());
        fm = getSupportFragmentManager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public int getTitleLayout() {
        return -1;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initBottomNavBar();//初始化导航栏
    }

    @Override
    protected void setListener() {


    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }

    //初始化导航栏
    private void initBottomNavBar() {
        bottomNavigationBar.clearAll();//清除
        //未读消息
        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(android.R.color.holo_red_light)
                .setText("99")
                .setHideOnSelect(false);
        //设置导航栏的模式和状态
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED).setBarBackgroundColor(R.color.nav_bg).setInActiveColor(R.color.nav_normal)
                .setActiveColor(R.color.nav_select);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //添加导航栏的每一项
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, R.string.main_botm_nav_interaction).
                        setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, R.string.main_botm_nav_teach))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, R.string.main_botm_nav_message))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, R.string.main_botm_nav_friend))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, R.string.main_botm_nav_my))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        //设置导航栏的点击监听
        bottomNavigationBar.setTabSelectedListener(this);
        //初始化第一次显示的导航栏
        fm.beginTransaction().add(R.id.layFrame,list.get(lastSelectedPosition)).commit();
    }

    //切换 fragment
    public void switchFragment(int last, int select) {
        if (last != select) {
            Fragment from = list.get(last);
            Fragment to = list.get(select);
            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                    android.R.anim.fade_in, android.R.anim.fade_out);
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.layFrame, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
    @Override
    public void onTabSelected(int position) {
        /**
         *每次添加之前隐藏所有正在显示的Fragment
         *然后如果是第一次添加的话使用transaction.add();
         *但第二次显示的时候,使用transaction.show();
         *这样子我们就可以保存Fragment的状态了
         */
        switchFragment(lastSelectedPosition,position);
        lastSelectedPosition = position;
    }
    @Override
    public void onTabUnselected(int position) {
        Log.e(TAG, "onTabUnselected: " + position);
    }
    @Override
    public void onTabReselected(int position) {
        Log.e(TAG, "onTabReselected: " + position);

    }
}
