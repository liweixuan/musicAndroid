package com.utopia.musicutopiaandroid.application;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者:Created by 简玉锋 on 2017/4/12 11:36
 * 邮箱: jianyufeng@38.hn
 * 应用入口
 */

public class App extends Application {
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initOkhttpClient();
    }

    /**
     * 配置网络请求的 Okhttp配置OkhttpClient
     */
    private void initOkhttpClient() {
        //Cookie(包含Session)  https://github.com/hongyangAndroid/okhttputils
        // PersistentCookieStore //持久化cookie
        //SerializableHttpCookie //持久化cookie
        //MemoryCookieStore //cookie信息存在内存中
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        //设置可访问所有的https网站
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        //设置具体的证书
        //HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputstream, null, null);
        //双向认证
        //HttpsUtils.getSslSocketFactory(证书的inputstream, 本地证书的inputstream,本地证书的密码)
        //配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggerInterceptor("TAG", true))//对于Log
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static App getInstance() {
        return sInstance;
    }

}
