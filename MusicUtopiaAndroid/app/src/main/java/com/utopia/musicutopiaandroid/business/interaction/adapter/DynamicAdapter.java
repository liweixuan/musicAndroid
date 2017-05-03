package com.utopia.musicutopiaandroid.business.interaction.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.application.App;
import com.utopia.musicutopiaandroid.business.interaction.bean.DynamicBean;
import com.utopia.musicutopiaandroid.framework.ui.RZFlowLayout;

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

    }


    //设置数据
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        int itemViewType = baseViewHolder.getItemViewType();
        //个人信息处理
        convertUserInfo(baseViewHolder, dynamicBean);
        //文本内容处理
        convertContent(baseViewHolder, dynamicBean);
        //图片信息处理
        convertImages(baseViewHolder, dynamicBean);
        //标签处理
        convertLabel(baseViewHolder, dynamicBean);
        //评论点赞处理
        convertComment(baseViewHolder, dynamicBean);
    }
  
  

    //个人信息处理
    private void convertUserInfo(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    //文本内容处理
    private void convertContent(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    //标签处理
    private void convertImages(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

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
