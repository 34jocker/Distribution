package com.jzbwlkj.distribution.mvp.model.base;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.utils.HttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：xym on 2016/12/2 09:18
 * 该类是一个抽取的基类
 * 主要用于实现网络请求的封装.为get.和post请求
 */

public abstract class BaseNet<T> {
    //作用:在子类初始化的时候就将子类的路径给初始化
    private  String childUrl;

    public BaseNet() {
        this. childUrl = getChildUrl();
    }

    /***
     * 无参数的get请求
     *
     * @param cacheKey 设置当前请求的缓存key,建议每个不同功能的请求设置一个
     */
    public void getNet(String cacheKey) {
        //获取都url,通过子类   `
//        String url = getChildUrl();
        //无参数的
        //无文件上传
        //有缓存.时间限制的
        OkGo.get(childUrl)
                .tag(this)
                .connTimeOut(10000)      // 设置当前请求的连接超时时间
                .readTimeOut(10000)      // 设置当前请求的读取超时时间
                .writeTimeOut(10000)     // 设置当前请求的写入超时时间
                .cacheKey(cacheKey)    // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheTime(5000)//显示为5s.单位为毫秒
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)//设置当前的缓存模式,.如果缓存不存在才请求网络，否则使用缓存
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        String result = response.body().toString();
                        //将获取到的数据交给子类解析
                        childParse(result);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
        //有缓存时间限制
        //请求后需要将数据解析
    }

    /**
     * 有参数的get请求
     *
     * @param cacheKey 缓存键
     * @param params   为拼接的请求参数
     *  list<String> 存储的为多个值或者一个值
     *   原因:有时候传递的参数是一对多的关系
     */
    public void getNet(String cacheKey, Map<String, List<String>> params) {
        //获取都url,从子类获取
//        String childUrl = getChildUrl();
        //有参数的.需要就行拼接
        String urlFromParams = HttpUtils.createUrlFromParams(childUrl, params);
        //有缓存.时间限制的
        //有缓存时间限制
        OkGo.get(urlFromParams)
                .tag(this)
                .connTimeOut(10000)      // 设置当前请求的连接超时时间
                .readTimeOut(10000)      // 设置当前请求的读取超时时间
                .writeTimeOut(10000)     // 设置当前请求的写入超时时间
                .cacheKey(cacheKey)    // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheTime(5000)//显示为5s.单位为毫秒
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)//设置当前的缓存模式,.如果缓存不存在才请求网络，否则使用缓存
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //获取到请求到的字符串进行解析
                        childParse(response.body().toString());
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
        //请求后需要将数据解析
    }

    /**
     * 无参数的post请求,不需要文件的上传
     */
    public void postNet(String cacheKey) {
        //获取都url,从子类获取
//        String childUrl = getChildUrl();
        Map<String, String> map = new HashMap<>();
        postNet(cacheKey, map);
    }

    /**
     * 实现post 请求带一个参数
     *
     * @param param      请求体的key
     * @param paramValue 请求体的value
     */
    public void postNet(String cacheKey, String param, String paramValue) {
        //获取都url,从子类获取
//        String childUrl = getChildUrl();
        //有一组参数
        Map<String, String> map = new HashMap<>();
        map.put(param, paramValue);
        postNet(cacheKey, map);
    }

    /**
     * 实现带有两个参数的post请求
     *
     * @param params   表示多组参数的集合
     * @param cacheKey 表示当前的缓存的键
     */
    public void postNet(String cacheKey, Map<String, String> params) {
        //获取都url,从子类获取
//        String childUrl = getChildUrl();
        //有2组参数
        OkGo.post(childUrl)//
                .tag(this)//
                .params(params)
                .connTimeOut(10000)      // 设置当前请求的连接超时时间
                .readTimeOut(10000)      // 设置当前请求的读取超时时间
                .writeTimeOut(10000)     // 设置当前请求的写入超时时间
                .cacheKey(cacheKey)    // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheTime(5000)         // 缓存的过期时间,单位毫秒
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST) // 缓存模式，详细请看第四部分，缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //将结果解析
                        childParse(response.body().toString());
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    /**
     * 表示将图片或者文件上传到网络,当前上传的为一个文件
     *
     * @param fileName 表示上传的文件名
     * @param file     表示上传的文件
     */
    public void postNetUP(String fileName, File file) {
        //获取都url,从子类获取
//        String childUrl = getChildUrl();
        //map 的作用是为了减少代码量和下面的上传进行结合,定义的一个空的集合
        Map<String, String> map = new HashMap<>();
        List<File> files = new ArrayList<>();
        files.add(file);
        postNetUP(fileName, files, map);
    }

    /**
     * 当前的上传网络是无参的.可以修改
     *
     * @param key   上传的键值
     * @param files 上传的文件集合
     * @param map   为参数集合
     */
    public void postNetUP(String key, List<File> files, Map<String, String> map) {
        //获取都url,从子类获取
//        String childUrl = getChildUrl();
        //有文件上传
        OkGo.post(childUrl)//
                .tag(this)//
                .params(map)        // 这里可以上传参数
                .addFileParams(key, files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                    }


                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
                    }
                });
    }

    /**
     * 该方法是获取到子类的URL
     *
     * @return 为子类的URL
     */
    public abstract String getChildUrl();

    protected abstract T childParse(String result);
}
