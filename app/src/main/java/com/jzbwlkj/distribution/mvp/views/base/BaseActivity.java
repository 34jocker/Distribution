package com.jzbwlkj.distribution.mvp.views.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * 作者:xym
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(initLayout());

        initView();
        initListener();
        initData();
    }

    /**
     * 初始化
     * @return
     */
    protected  abstract  int initLayout();
    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化view
     */
    protected abstract void initView();
}
