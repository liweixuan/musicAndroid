package com.utopia.musicutopiaandroid.business.interaction.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.utopia.musicutopiaandroid.R;
import com.utopia.musicutopiaandroid.application.App;
import com.utopia.musicutopiaandroid.business.interaction.bean.DynamicBean;
import com.utopia.musicutopiaandroid.framework.comm.util.DensityUtil;
import com.utopia.musicutopiaandroid.framework.ui.RZFlowLayout;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.maxHeight;
import static android.R.attr.maxWidth;
import static android.R.attr.minHeight;
import static android.R.attr.minWidth;

/**
 * Created by Administrator on 2017/4/21/021.
 */

public class DynamicAdapter extends BaseMultiItemQuickAdapter<DynamicBean> {

    private String[] mLabels = {"购物", "美食", "游玩", "北京", "CSDN", "Airsaid", "周游", "新春送祝福", "周游", "新春送祝福", "周游", "新春送祝福"};

    //多种类型的布局
    public DynamicAdapter(List<DynamicBean> data) {
        super(data);
        addItemType(ItemType.type_text, R.layout.dynamic_item_text);//纯文本
        addItemType(ItemType.type_text_img, R.layout.dynamic_item_text_img);//文本和图片
        addItemType(ItemType.type_text_music, R.layout.dynamic_item_text_music);//文本音乐
        addItemType(ItemType.type_text_video, R.layout.dynamic_item_text_video);//文本视频

    }

    //清除所有数据
    public void clear() {
        mData.clear();
        this.notifyDataSetChanged();
    }

    //设置数据
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        //个人信息处理
        convertUserInfo(baseViewHolder, dynamicBean);
        //标签处理
        convertLabel(baseViewHolder, dynamicBean);
        //评论点赞处理
        convertComment(baseViewHolder, dynamicBean);

        int itemViewType = baseViewHolder.getItemViewType();
        switch (itemViewType) {
            case ItemType.type_text://只有文本
                //文本内容处理
                convertContent(baseViewHolder, dynamicBean);
                break;
            case ItemType.type_text_img://文本和一张图片
                //图片信息处理
                convertImage(baseViewHolder, dynamicBean);
                break;
            case ItemType.type_text_music://文本和多张图片
                //音乐信息处理
                convertMusic(baseViewHolder, dynamicBean);
                break;
            case ItemType.type_text_video://文本和多张图片
                //视频信息处理
                convertVideo(baseViewHolder, dynamicBean);
                break;

        }

    }


    //个人信息处理
    private void convertUserInfo(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
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

    //评论点赞处理
    private void convertComment(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
    }

    //文本内容处理
    private void convertContent(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        String name = dynamicBean.getName();
        if (name == null) { //确保不蹦
            baseViewHolder.setText(R.id.content_text_id, "");
            return;
        }
        baseViewHolder.setText(R.id.content_text_id, name);
    }

    //图片内容处理
    private void convertImage(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

        LinearLayout view = baseViewHolder.getView(R.id.imgs_id);
        view.removeAllViews();
        //处理图
        ArrayList<String> imgs = dynamicBean.getImgs();
        if (imgs == null) { //没有图
            //隐藏图
            baseViewHolder.setVisible(R.id.imgs_id, false);
            return;
        }
        //有图 分类处理
        baseViewHolder.setVisible(R.id.imgs_id, true);
        int num = imgs.size();
        /*
        根据图片的数量进行不同的显示方式
        0 隐藏图片的显示
        1 张图片是显示一张 尺寸相当的图片
        2
        3 一行
        4 两行 两个
        5
        6
        7
        8
        9 正常
        * */
        if (num == 0) {
            baseViewHolder.setVisible(R.id.imgs_id, false);//隐藏
        } else {
            baseViewHolder.setVisible(R.id.imgs_id, true);//显示
            if (num == 1) { //是一个 添加一张较大的图
                ImageView bigImg = new ImageView(mContext);
                bigImg.setImageResource(R.mipmap.ic_launcher);
//                bigImg = new ImageView(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                bigImg.setLayoutParams(lp);
                bigImg.setMaxHeight(DensityUtil.dip2px(300));
                bigImg.setMaxWidth(DensityUtil.dip2px(300));
                bigImg.setMinimumHeight(DensityUtil.dip2px(148));
                bigImg.setMinimumWidth(DensityUtil.dip2px(148));
                view.addView(bigImg);
            } else {
                if (num > 1 && num <= 9) {
                    //创建布局
                    //添加图
                    if (num == 4) {
                        createImgs(view, 2, imgs);
                    } else {
                        createImgs(view, 3, imgs);
                    }
                } else {
                    LinearLayout lastH = createImgs(view, 3, imgs.subList(0, 8));
                    //添加更多
                    View contain = mLayoutInflater.inflate(R.layout.dynamic_img_more, null);
                    int screeWidth = DensityUtil.getDisplayWidth(mContext)/3-DensityUtil.dip2px(26);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.width = screeWidth;
                    lp.height = screeWidth;
                    contain.setLayoutParams(lp);
                    TextView moreNum = (TextView) contain.findViewById(R.id.more_num_id);
                    moreNum.setText(String.format(mContext.getString(R.string.more_img), num - 9));
                    lastH.addView(contain);
                }
            }
        }
    }

    //创建水平的图片容器
    private LinearLayout createHorLineart() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout lt = new LinearLayout(mContext);
        lp.bottomMargin = DensityUtil.dip2px(3);
        lt.setLayoutParams(lp);
        lt.setOrientation(LinearLayout.HORIZONTAL);
        lt.setGravity(Gravity.LEFT);
        return lt;
    }

    /**
     * 返回最后一个
     *
     * @param v
     * @param lieMax
     * @param imgs
     * @return
     */
    private LinearLayout createImgs(ViewGroup v, int lieMax, List imgs) {
        int num = imgs.size();
        int lastHang = num % lieMax;//最后一行
        int hang = num / lieMax;//满行
        //添加满行
        LinearLayout lt = null;
        int screeWidth = DensityUtil.getDisplayWidth(mContext)/3-DensityUtil.dip2px(26);
        for (int i = 0; i < hang; i++) {
            lt = createHorLineart();
            //添加图片
            for (int j = 0; j < lieMax; j++) {
                ImageView img = new ImageView(mContext);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                img.setImageResource(R.mipmap.ic_launcher);
                img.setBackgroundColor(Color.RED);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.width = screeWidth;
                lp.height = screeWidth;
                lp.rightMargin = DensityUtil.dip2px(3);
                img.setLayoutParams(lp);
                lt.addView(img);
            }
            v.addView(lt);
        }
        //添加最后一行
        if (lastHang == 0) {
            return lt;
        }
        lt = createHorLineart();
        for (int i = 0; i < lastHang; i++) {
            //添加图片
            ImageView img = new ImageView(mContext);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageResource(R.mipmap.ic_launcher);
            img.setBackgroundColor(Color.RED);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.width = screeWidth;
            lp.height = screeWidth;
            lp.rightMargin = DensityUtil.dip2px(3);
            img.setLayoutParams(lp);
            lt.addView(img);
        }
        v.addView(lt);
        return lt;
    }

    //音乐内容处理
    private void convertMusic(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {

    }

    //音乐内容处理
    private void convertVideo(BaseViewHolder baseViewHolder, DynamicBean dynamicBean) {
        //一张视频的适当图片 带播放的按钮
    }


}
