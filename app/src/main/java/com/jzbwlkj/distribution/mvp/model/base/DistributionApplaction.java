package com.jzbwlkj.distribution.mvp.model.base;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;

import java.util.logging.Level;

/**
 * 作者：admin on 2016/12/2 09:50
 */

public class DistributionApplaction extends Application {
    //上下文
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        mContext  = getApplicationContext();

        //必须调用初始化
        OkGo.init(this);

        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)
                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates() ;                         //方法一：信任所有证书,不安全有风险

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getmContext() {
        return mContext;
    }
}
