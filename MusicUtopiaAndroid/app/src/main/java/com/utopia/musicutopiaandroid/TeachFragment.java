package com.utopia.musicutopiaandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 17:07
 * 邮箱: jianyufeng@38.hn
 */
public class TeachFragment extends Fragment {
    @BindView(R.id.tv)
    TextView tv;
    private String title;

    public static TeachFragment getInstance(String title) {

        TeachFragment sInstance = new TeachFragment();
        sInstance.title = title;
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interaction, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv.setText(title);
    }
}
