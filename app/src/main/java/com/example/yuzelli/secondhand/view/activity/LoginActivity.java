package com.example.yuzelli.secondhand.view.activity;

import android.content.Context;
import android.content.Intent;
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
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;
import com.example.yuzelli.secondhand.utils.LoginUtils;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends BaseActivity {
    private  EditText et_phone;
    private  EditText et_password;
    private TextView tv_login;
    private  ImageView cb_agree;
    private boolean agreeFlag = true;
    private Context context;




    @Override
    protected int layoutInit() {
        return R.layout.activity_login;
    }

    @Override
    protected void binEvent() {
        context = this;
        et_phone = (EditText) this.findViewById(R.id.et_phone);
        et_password = (EditText) this.findViewById(R.id.et_password);
        tv_login = (TextView) this.findViewById(R.id.tv_login);

        this.findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.actionStart(context);
            }
        });
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
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agreeFlag) {
                    doLoginAction();
                } else {
                    Toast.makeText(context, "请同意协议", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void fillData() {

    }

    private void doLoginAction() {
        String phone = et_phone.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (phone == null || phone.equals("")) {
            Toast.makeText(context, "请输入手机号", Toast.LENGTH_SHORT).show();
        }
        if (password == null || password.equals("")) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
        }
        if (LoginUtils.isPhoneEnable(phone)) {
           doLoginRequest(phone,password);
        } else {
            Toast.makeText(context, "请正确的输入手机号", Toast.LENGTH_SHORT).show();
        }
    }
    /**
      网络请求
     */
    private void doLoginRequest(String userPhone,String passWord) {
        List<UserInfo>list = (List<UserInfo>) SharePreferencesUtil.readObject(this, ConstantsUtils.USER_LIST);
        if (list==null){
            list = new ArrayList<>();
        }
        for (UserInfo u : list){
            if (u.getPhone().equals(userPhone)&&u.getPass().equals(passWord)){
                SharePreferencesUtil.saveObject(this,ConstantsUtils.CURRENT_USER,u);
                MainActivity.actionStart(this);
            }
        }

    }
    public static void actionStart(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }


}
