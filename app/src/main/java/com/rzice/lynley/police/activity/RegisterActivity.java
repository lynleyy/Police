package com.rzice.lynley.police.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rzice.lynley.police.R;
import com.rzice.lynley.police.base.BaseActivity;
import com.rzice.lynley.police.utils.CommonUtil;
import com.rzice.lynley.police.utils.MD5Utils;
import com.rzice.lynley.police.utils.PhoneUtil;
import com.rzice.lynley.police.utils.TimeCountUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_register_account)
    EditText et_account;
    @BindView(R.id.et_register_yzm)
    EditText et_yzm;
    @BindView(R.id.btn_register_yzm)
    Button btn_yzm;
    @BindView(R.id.et_register_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_register_signup)
    Button btn_signup;
    private int random;//验证码
private   String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init_title();
        changeTitle("注册", null);
    }

    @Override
    public void init_title() {
        super.init_title();
    }

    @Override
    public void doClick(View v) {
        super.doClick(v);
        switch (v.getId()) {
            case R.id.btn_register_yzm:
               phone = et_account.getText().toString().trim();
                if (CommonUtil.isMobileNumber(phone)) {
                    TimeCountUtil TimeCountUtil = new TimeCountUtil(this, 60000, 1000, btn_signup);
                    TimeCountUtil.start();
                } else {
                    showToast(String.valueOf(CommonUtil.isMobileNumber(et_account.getText().toString())));
                    return;
                }
                // TODO: http request.
                String username = "benxiong1990";//短信宝帐户名
                String passs = MD5Utils.md5("Zse4rfvgy7");//短信宝帐户密码，md5加密后的字符串
                random = (int) ((Math.random() * 9 + 1) * 100000);//获取随机数
                String content = "\"【好享一元】您申请注册的验证码为:\" " + random + "\"（如非本人操作请忽略，本条免费）\"";
                //java.net.URLEncoder.encode();//发送内容
                Map<String, String> map = new HashMap<>();
                map.put("u", username);
                map.put("p", passs);
                map.put("m", phone);
                map.put("c", content);
                OkHttpUtils.post().params(map).url("http://www.cocsms.com/sms").build().connTimeOut(6000).execute(
                        new StringCallback() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                showToast("发送失败");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                showToast("发送成功");
                            }
                        }
                );
                break;
            case R.id.btn_register_signup:
                String pwd = et_pwd.getText().toString().trim();
                String yzm = et_yzm.getText().toString().trim();
                if(!PhoneUtil.Phone(phone).equals("")){
                    showToast(PhoneUtil.Phone(phone));
                    return;
                }else if(yzm.equals("")){
                    showToast("请输入验证码");
                    return;
                }else if(!yzm.equals(String.valueOf(random))){
                    showToast("输入验证码不对");
                    return;
                }else if(pwd.equals("")){
                    showToast("请输入密码");
                    return;
                }

                Map<String,String> map1 =new HashMap<>();
                OkHttpUtils.post().params(map1).url("http://www.cocsms.com/sms").build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
                break;
        }
    }
}
