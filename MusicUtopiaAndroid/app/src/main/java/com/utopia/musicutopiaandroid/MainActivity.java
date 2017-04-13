package com.utopia.musicutopiaandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 12:00
 * 邮箱: jianyufeng@38.hn
 * 主界面
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private static final String TAG = "MainActivity";
    //底部导航
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    //选择的导航 默认是第一个
    int lastSelectedPosition = 0;

    //未读消息
    private BadgeItem numberBadgeItem;

    private List<Fragment> list;
    private InteractionFragment interactionFragment;
    private TeachFragment teachFragment;
    private MessageFragment messageFragment;
    private FriendFragment friendFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        bottomNavigationBar.clearAll();//清除

        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(android.R.color.holo_red_light)
                .setText("99")
                .setHideOnSelect(false);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED).setBarBackgroundColor(R.color.nav_bg).setInActiveColor(R.color.nav_normal)
        .setActiveColor(R.color.nav_select);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "互动").
                        setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "教学"))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "好友"))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        onTabSelected(lastSelectedPosition);
    }

    /**
     * 隐藏所有正在显示的Fragment
     * @param transaction
     */
    public void hideFragment(FragmentTransaction transaction) {
        for (Fragment fragment : list) {
            transaction.hide(fragment);
        }
    }
    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        /**
         *每次添加之前隐藏所有正在显示的Fragment
         *然后如果是第一次添加的话使用transaction.add();
         *但第二次显示的时候,使用transaction.show();
         *这样子我们就可以保存Fragment的状态了
         */
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (interactionFragment == null) {
                    interactionFragment =  InteractionFragment.getInstance("互动");
                    transaction.add(R.id.layFrame, interactionFragment);
                    list.add(interactionFragment);
                } else {
                    transaction.show(interactionFragment);
                }
                break;
            case 1:
                if (teachFragment == null) {
                    teachFragment = TeachFragment.getInstance("教学");
                    transaction.add(R.id.layFrame, teachFragment);
                    list.add(teachFragment);
                } else {
                    transaction.show(teachFragment);
                }
                break;
            case 2:
                if (messageFragment == null) {
                    messageFragment = MessageFragment.getInstance("消息");
                    transaction.add(R.id.layFrame, messageFragment);
                    list.add(messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 3:
                if (friendFragment == null) {
                    friendFragment = FriendFragment.getInstance("好友");
                    transaction.add(R.id.layFrame, friendFragment);
                    list.add(friendFragment);
                } else {
                    transaction.show(friendFragment);
                }
                break;
            case 4:
                if (myFragment == null) {
                    myFragment = MyFragment.getInstance("好友");
                    transaction.add(R.id.layFrame, myFragment);
                    list.add(myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();



    }

    @Override
    public void onTabUnselected(int position) {
        Log.e(TAG, "onTabUnselected: "+position );
    }

    @Override
    public void onTabReselected(int position) {
        Log.e(TAG, "onTabReselected: "+position );

    }
}
