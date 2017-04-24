package com.utopia.musicutopiaandroid.application;

import android.app.Application;

import com.utopia.musicutopiaandroid.business.interaction.frament.dynamic.model.engine.Engine;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 11:36
 * 邮箱: jianyufeng@38.hn
 * 应用入口
 */

public class App extends Application {
    private static App sInstance;
    private Engine mEngine;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mEngine = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }
    public static App getInstance() {
        return sInstance;
    }
    public Engine getEngine() {
        return mEngine;
    }
}
