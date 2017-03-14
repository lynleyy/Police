package com.rzice.lynley.police;

import android.content.Context;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者： XMZ on 2017/3/13 13:21.
 * 邮箱：Lynley1207@163.com
 */

public class Application extends android.app.Application{
    private static Context mContext;
    private  static  Application instance;


    public  static  synchronized  Application getInstance(){

        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;

//
//        CrashManager.install(this);
        NoHttp.initialize(this);
        // 开始NoHttp的调试模式, 这样就能看到请求过程和日志
        Logger.setTag("Business_Loan");
        Logger.setDebug(true);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }




    public  static  Context getContext(){

        return mContext;
    }

}
