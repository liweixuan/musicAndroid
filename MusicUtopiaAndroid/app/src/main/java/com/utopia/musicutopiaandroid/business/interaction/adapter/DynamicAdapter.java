package com.utopia.musicutopiaandroid.business.interaction.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
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
        addItemType(ItemType.type_text, R.layout.dynamic_item_text);
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
class ss extends GridLayoutManager{
    public ss(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setSmoothScrollbarEnabled(false);
    }

    public ss(Context context, int spanCount) {
        super(context, spanCount);
        setSmoothScrollbarEnabled(false);

    }

    public ss(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        setSmoothScrollbarEnabled(false);

    }
}
    private void convertImages9(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
//        ArrayList imgs = dynamicBean.getImgs(); //获取总数
//        int totalNum = imgs.size();
//        if (totalNum==0){
//            //不显示图片
//        }else if (totalNum==
//
//
//
//        Context context = App.getInstance();
//
//        recyclerview.setAdapter(new ArrayAdapter<String>(App.getInstance(),-1,imgs){
//            @NonNull
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                return LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_img,parent,false);
//            }
//        });

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
