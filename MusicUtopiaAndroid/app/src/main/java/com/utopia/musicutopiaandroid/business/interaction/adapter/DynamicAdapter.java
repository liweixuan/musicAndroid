package com.utopia.musicutopiaandroid.business.interaction.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.application.App;
import com.utopia.musicutopiaandroid.business.interaction.bean.DynamicBean;
import com.utopia.musicutopiaandroid.framework.comm.util.DensityUtil;
import com.utopia.musicutopiaandroid.framework.ui.RZFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21/021.
 */

public class DynamicAdapter extends BaseMultiItemQuickAdapter<DynamicBean> {

    private String[] mLabels = {"购物", "美食", "游玩", "北京", "CSDN", "Airsaid", "周游", "新春送祝福", "周游", "新春送祝福", "周游", "新春送祝福"};

    //多种类型的布局
    public DynamicAdapter(List<DynamicBean> data) {
        super(data);
        addItemType(ItemType.type_text, R.layout.dynamic_item_normal);
        addItemType(ItemType.type_tex_img, R.layout.dynamic_item_text_img);
        addItemType(ItemType.type_tex_img9, R.layout.dynamic_item_text_img9);

    }

    //设置数据
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        int itemViewType = baseViewHolder.getItemViewType();
        switch (itemViewType){
            case ItemType.type_text://只有文本
                //文本内容处理
                convertContent(baseViewHolder, dynamicBean);
                break;
            case ItemType.type_tex_img://文本和一张图片
                //图片信息处理
                convertImage(baseViewHolder, dynamicBean);
                break;
            case ItemType.type_tex_img9://文本和多张图片
                //图片9信息处理
                convertImages9(baseViewHolder, dynamicBean);
                break;

        }
        //个人信息处理
        convertUserInfo(baseViewHolder, dynamicBean);
        //标签处理
        convertLabel(baseViewHolder, dynamicBean);
        //评论点赞处理
        convertComment(baseViewHolder, dynamicBean);
    }

    private void convertImages9(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        int spanCount = 4;
        Context context = App.getInstance();
        RecyclerView recyclerview=baseViewHolder.getView(R.id.recycler_view);
        ArrayList imgs = dynamicBean.getImgs();
//        //每行显示3个，水平显示
       recyclerview.setLayoutManager(new GridLayoutManager(context,spanCount));
//        ViewGroup.LayoutParams layoutParams = recyclerview.getLayoutParams();
//        //计算行数
//        int lineNumber = imgs.size()%spanCount==0? imgs.size()/spanCount:imgs.size()/spanCount +1;
//        //计算高度=行数＊每行的高度 ＋(行数－1)＊10dp的margin ＋ 10dp（为了居中）
//        //因为每行显示4个条目，为了保持正方形，那么高度应该是也是宽度/3
//        layoutParams.height = lineNumber *(DensityUtil.dip2px(60)) + (lineNumber)*DensityUtil.dip2px(60);
//        recyclerview.setLayoutParams(layoutParams);
        recyclerview.setBackgroundResource(R.color.colorPrimary);
        recyclerview.setAdapter(new BaseQuickAdapter<String>(R.layout.dynamic_img,imgs) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, String o) {

            }
        });

    }


    //个人信息处理
    private void convertUserInfo(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    //文本内容处理
    private void convertContent(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    //标签处理
    private void convertImage(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    //评论点赞处理
    private void convertComment(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    // 动态添加标签
    private void convertLabel(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        RZFlowLayout view = baseViewHolder.getView(R.id.rz_container_id);
        view.removeAllViews();
        MarginLayoutParams layoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 10, 5);// 设置边距
        for (int i = 0; i < mLabels.length; i++) {
            final TextView textView = new TextView(App.getInstance());
            textView.setTag(i);
            textView.setTextSize(14);
            textView.setText(mLabels[i]);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.select_round_btn_blue);
            view.addView(textView, layoutParams);
            // 标签点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(App.getInstance(), mLabels[(int) textView.getTag()], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //清除所有数据
    public void clear() {
        mData.clear();
        this.notifyDataSetChanged();
    }

   
}
