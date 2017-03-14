package com.rzice.lynley.police.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rzice.lynley.police.HomePage;
import com.rzice.lynley.police.R;
import com.rzice.lynley.police.base.BaseActivity;
import com.rzice.lynley.police.utils.CommonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_name;
    private EditText et_pwd;
    private TextView tv_forget;
    private TextView tv_signup;
    Button btn_denglu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init_title();
        setToolbarVisibility(false);
        findView();
        //   changeTitle("登录", null);
    }

//    @Override
//    public void init_title() {
//        super.init_title();
////
////        btn_denglu.setBackgroundResource(R.drawable.rec_bg_gray);
////        btn_denglu.setClickable(false);
////
////        et_name.addTextChangedListener(this);
////        et_pwd.addTextChangedListener(this);
//
//    }

    private void findView() {
        et_name = (EditText) findViewById(R.id.et_login_name);
        et_pwd = (EditText) findViewById(R.id.et_login_pwd);
        tv_forget = (TextView) findViewById(R.id.tv_login_forget);
        tv_forget.setOnClickListener(this);
        tv_signup = (TextView) findViewById(R.id.tv_login_signup);
        tv_signup.setOnClickListener(this);
        btn_denglu = (Button) findViewById(R.id.btn_login_denglu);
        btn_denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget:

                startActivity(ForgetActivity.class);
                break;
            case R.id.tv_login_signup:

                startActivity(RegisterActivity.class);
                break;
            case R.id.btn_login_denglu:
                final String name = et_name.getText().toString().trim();
                final String pwd = et_pwd.getText().toString().trim();
                if (!CommonUtil.isMobileNumber(name)) {
                    showToast("手机号码格式错误");
                    // showAnmintion();
                    return;
                }
                if (pwd.length() < 6) {
//                    CommonUtil.showToask(getActivity(), );
                    //   showAnmintion();
                    showToast("密码长度不少于6位");
                    return;
                }
                String url = "http://192.168.0.116/JWB/login/appLogin.do";
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("username", name)
                        .addParams("password", pwd)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
//                                CommonUtil.showToask(getActivity(), );
                                showToast("错了");
                                Log.i("kkk", e.getMessage() + "*******" + id);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("kkkk", response);
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    if (jsonObject.getInt("statusCode") == 200) {
//                                        Toast.makeText(getActivity(),jsonObject.getString(), Toast.LENGTH_LONG).show();
                                        showToast(jsonObject.get("message") + "");
                                        startActivity(HomePage.class);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        });

                break;
        }
    }


}


/**
 * 需要监听 edittext的 变化
 *
 * @param s
 * @param start
 * @param before
 * @param count
 */
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (!TextUtils.isEmpty(et_name.getText().toString())
//                && !TextUtils.isEmpty(et_pwd.getText().toString())) {
//            btn_denglu.setBackgroundResource(R.drawable.rec_bg_blue);
//            btn_denglu.setClickable(true);
//        } else {
//            btn_denglu.setBackgroundResource(R.drawable.rec_bg_gray);
//            btn_denglu.setClickable(false);
//        }
//    }

//
//    /**
//     * 显示出错的动画效果
//     */
//    public void showAnmintion() {
//        //                 动画效果
//        YoYo.with(Techniques.Shake).duration(700).playOn(layAccPw);
//    }


