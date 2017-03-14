package com.rzice.lynley.police.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rzice.lynley.police.R;
import com.rzice.lynley.police.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetActivity extends BaseActivity {
    @BindView(R.id.et_register_account)
    EditText et_account;
    @BindView(R.id.et_register_yzm)
    EditText et_yzm;
    @BindView(R.id.btn_forget_yzm)
    Button btn_yzm;
    @BindView(R.id.et_register_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_forget_confirm)
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        init_title();
        changeTitle("修改密码", null);
    }

    @Override
    public void init_title() {
        super.init_title();
    }
}
