package com.jzbwlkj.distribution.mvp.views.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jzbwlkj.distribution.R;
import com.jzbwlkj.distribution.mvp.views.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.tv_register_lg);
    }
}
