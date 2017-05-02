package com.utopia.musicutopiaandroid.business.interaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.DynamicFragment;
import com.utopia.musicutopiaandroid.business.interaction.frament.match.MatchFragment;
import com.utopia.musicutopiaandroid.business.interaction.frament.organization.OrganizationFragment;
import com.utopia.musicutopiaandroid.business.interaction.frament.partner.PartnerFragment;
import com.utopia.musicutopiaandroid.framework.base.fragment.BaseFragment;
import com.utopia.musicutopiaandroid.framework.comm.adapter.FragmentVPAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 17:07
 * 邮箱: jianyufeng@38.hn
 */
public class InteractionFragment extends BaseFragment {

    @BindView(R.id.contain_vp)
    ViewPager mViewPAger;
    @BindView(R.id.dynamic_tv)
    TextView dynamicNav;//动态
    @BindView(R.id.partner_tv)
    TextView partnerNav;//伙伴
    @BindView(R.id.organize_tv)
    TextView organizeNav;//团体
    @BindView(R.id.match_tv)
    TextView matchNav;//比赛
    @BindView(R.id.vp_indicator)
    View indictor;

    private ArrayList<Fragment> fragmentList;
    private int currIndex = 0;//当前页卡编号
    private int  offsetWidth =0;

    @Override
    protected int getLayoutId() {
        return R.layout.interaction_fragment;
    }

    @Override
    protected void initConfig() {
        fragmentList = new ArrayList<Fragment>();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mViewPAger.addOnPageChangeListener(new PageChangeListener());//页面变化时的监听器
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        InitViewPager();
        setIndictorWidth();
    }
    //设置indictor指示器的宽度
    private void setIndictorWidth() {
        final View v=getViewById(R.id.top_nav_layout);
        v.post(new Runnable() {
            @Override
            public void run() {
               int  width = v.getWidth()/4;
                ViewGroup.LayoutParams lp = indictor.getLayoutParams();
                offsetWidth=lp.width = width;
                indictor.setLayoutParams(lp);
            }
        });
    }

    @Override
    protected void onLazyLoadOnce() {
        super.onLazyLoadOnce();

    }
    /*
     * 初始化ViewPager
     */
    public void InitViewPager() {
        Fragment dynamicFragment = new DynamicFragment();//动态
        Fragment partnerFragment = new PartnerFragment();//伙伴
        Fragment organizationFragment = new OrganizationFragment();//团体
        Fragment matchFragment = new MatchFragment();//比赛
        fragmentList.add(dynamicFragment);
        fragmentList.add(partnerFragment);
        fragmentList.add(organizationFragment);
        fragmentList.add(matchFragment);
        //给ViewPager设置适配器
        mViewPAger.setAdapter(new FragmentVPAdapter(getChildFragmentManager(), fragmentList));
        mViewPAger.setCurrentItem(currIndex);//设置当前显示标签页为第一页
    }
    //导航栏点击事件
    @OnClick({R.id.dynamic_tv, R.id.partner_tv, R.id.organize_tv, R.id.match_tv})
     void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.dynamic_tv:
                currIndex = 0;
                break;
            case R.id.partner_tv:
                currIndex = 1;
                break;
            case R.id.organize_tv:
                currIndex = 2;
                break;
            case R.id.match_tv:
                currIndex = 3;
                break;
        }
        mViewPAger.setCurrentItem(currIndex,true);

    }


    //处理ViewPager的滑动
    class PageChangeListener implements ViewPager.OnPageChangeListener {



        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //指示器滑动
            indictor.setTranslationX((offsetWidth) * (positionOffset + position));
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

}
