package com.example.yuzelli.secondhand.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseActivity;
import com.example.yuzelli.secondhand.bean.UserInfo;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.OkHttpClientManager;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;
import com.example.yuzelli.secondhand.utils.LoginUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;


public class RegisterActivity extends BaseActivity {

    private EditText et_phone;
    private EditText et_password;
    private EditText et_ok_password;

    private TextView tv_register;

    private ImageView cb_agree;
    private boolean agreeFlag = true;
    private Context context;

    @Override
    protected int layoutInit() {
        return R.layout.activity_register;
    }

    @Override
    protected void binEvent() {
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        context = this;
        et_phone = (EditText) this.findViewById(R.id.et_phone);
        et_password = (EditText) this.findViewById(R.id.et_password);
        et_ok_password = (EditText) this.findViewById(R.id.et_ok_password);

        tv_register = (TextView) this.findViewById(R.id.tv_register);
        cb_agree = (ImageView) this.findViewById(R.id.cb_agree);
        cb_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreeFlag = !agreeFlag;
                if (agreeFlag){
                    cb_agree.setImageResource(R.drawable.checkbox_pressed);
                }else {
                    cb_agree.setImageResource(R.drawable.checkbox_normal);
                }
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (agreeFlag) {
                    doRegisterAction();
                } else {
                    Toast.makeText(context, "请同意协议", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView tv_title = (TextView) this.findViewById(R.id.tv_title);

    }

    @Override
    protected void fillData() {

    }

    private void doRegisterAction() {
        String phone = et_phone.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String ok_password = et_ok_password.getText().toString().trim();

        if (phone == null || phone.equals("")) {
            Toast.makeText(context, "请输入手机号", Toast.LENGTH_SHORT).show();
        }
        if (password == null || password.equals("")) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
        }
        if (ok_password == null || ok_password.equals("")) {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
        }
        if (!ok_password.equals(password)) {
            Toast.makeText(context, "密码不一致", Toast.LENGTH_SHORT).show();
        }

            if (LoginUtils.isPhoneEnable(phone)) {
               doRegister(phone,password);
            } else {
                Toast.makeText(context, "输入手机号错误", Toast.LENGTH_SHORT).show();
            }


    }

    private void doRegister(String userPhone,String passWord) {
        List<UserInfo> list = null;
        list = (List<UserInfo>) SharePreferencesUtil.readObject(this,ConstantsUtils.USER_LIST);
        if (list==null){
            list = new ArrayList<>();
        }
        for (UserInfo u : list){
            if (u.getPhone().equals(userPhone)&&u.getPass().equals(passWord)){
               showToast("已经注册了！");
                return;
            }
        }
        UserInfo u = new UserInfo();
        u.setPhone(userPhone);
        u.setPass(passWord);
        list.add(u);
        SharePreferencesUtil.saveObject(this, ConstantsUtils.USER_LIST,list);
        finish();
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
