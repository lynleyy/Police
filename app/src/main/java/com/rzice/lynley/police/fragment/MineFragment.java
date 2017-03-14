package com.rzice.lynley.police.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rzice.lynley.police.R;
import com.rzice.lynley.police.activity.ForgetActivity;
import com.rzice.lynley.police.activity.RegisterActivity;
import com.rzice.lynley.police.utils.CommonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public class MineFragment extends Fragment implements View.OnClickListener {
    private EditText et_name;
    private EditText et_pwd;
    private TextView tv_forget;
    private TextView tv_signup;
    Button btn_denglu;
    private Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        findView(view);
        return view;
    }

    private void findView(View view) {
        et_name = (EditText) view.findViewById(R.id.et_login_name);
        et_pwd = (EditText) view.findViewById(R.id.et_login_pwd);
        tv_forget = (TextView) view.findViewById(R.id.tv_login_forget);
        tv_forget.setOnClickListener(this);
        tv_signup = (TextView) view.findViewById(R.id.tv_login_signup);
        tv_signup.setOnClickListener(this);
        btn_denglu = (Button) view.findViewById(R.id.btn_login_denglu);
        btn_denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget:
                intent = new Intent(getActivity(), ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_signup:
                intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login_denglu:
                final String name = et_name.getText().toString().trim();
                final String pwd = et_pwd.getText().toString().trim();
                if (!CommonUtil.isMobileNumber(name)) {
                    CommonUtil.showToask(getActivity(), "手机号码格式错误");
                    // showAnmintion();
                    return;
                }
                if (pwd.length() < 6) {
                    CommonUtil.showToask(getActivity(), "密码长度不少于6位");
                    //   showAnmintion();
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
                                CommonUtil.showToask(getActivity(), "错了");
                                Log.i("kkk", e.getMessage() + "*******" + id);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("kkkk", response);
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    if (jsonObject.getInt("statusCode") == 200) {
                                        Toast.makeText(getActivity(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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
