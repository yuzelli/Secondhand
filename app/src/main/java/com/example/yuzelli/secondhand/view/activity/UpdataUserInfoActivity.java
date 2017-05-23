package com.example.yuzelli.secondhand.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseActivity;
import com.example.yuzelli.secondhand.bean.UserInfo;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;
import com.example.yuzelli.secondhand.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 51644 on 2017/5/21.
 */

public class UpdataUserInfoActivity extends BaseActivity {
    @BindView(R.id.rw_head)
    ImageView rwHead;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_phoneNum)
    TextView tvPhoneNum;
    @BindView(R.id.tv_zhuanye)
    TextView tvZhuanye;
    @BindView(R.id.tv_nianji)
    TextView tvNianji;
    private UserInfo userInfo;
    @OnClick({ R.id.rl_userName, R.id.rl_zhuanye, R.id.rl_nianji,R.id.tv_ok})
    public void viewClick(View v) {
         switch (v.getId()){

             case R.id.rl_userName:
                 showUpDialog(0);

                 break;
             case R.id.rl_zhuanye:
                 showUpDialog(1);

                 break;
             case R.id.rl_nianji:
                 showUpDialog(2);

                 break;
             case R.id.tv_ok:
                 userInfo.setName(tvUserName.getText().toString().trim());
                 userInfo.setZhuangye(tvZhuanye.getText().toString().toString());
                 userInfo.setNiaji(tvNianji.getText().toString().trim());
                 updataUserInfo();
                 break;
             default:
                 break;

         }
    }

    private void updataUserInfo() {
        List<UserInfo> list = (List<UserInfo>) SharePreferencesUtil.readObject(this, ConstantsUtils.USER_LIST);
        if (list==null){
            list = new ArrayList<>();
        }
        int where = 0 ;
        for (int i = 0 ; i < list.size();i++){
            UserInfo u = list.get(i);
            if (u.getPhone().equals(userInfo.getPhone())&&u.getPass().equals(userInfo.getPass())){
                  where = i;
                break;
            }
        }
        list.remove(where);
        list.add(userInfo);
        SharePreferencesUtil.saveObject(this, ConstantsUtils.USER_LIST,list);
        SharePreferencesUtil.saveObject(this,ConstantsUtils.CURRENT_USER,userInfo);
        showToast("修改成功！");
    }

    private void showUpDialog(final int i) {
        final Dialog dialog = new Dialog(this,R.style.PhotoDialog);
        final View view = LayoutInflater.from(UpdataUserInfoActivity.this).inflate(R.layout.personal_head_select_diallog,null);
        dialog.setContentView(view);
        TextView tv_Cancel =  (TextView)view.findViewById(R.id.tv_cancel);
        TextView tv_title =  (TextView)view.findViewById(R.id.tv_title);
        if (i==0){
            tv_title.setText("修改用户名");
        }else if(i==1){
            tv_title.setText("修改专业");
        }else if(i==2){
            tv_title.setText("修改年级");
        }
        TextView tv_ok =  (TextView)view.findViewById(R.id.tv_ok);
        final EditText et_input =  (EditText)view.findViewById(R.id.et_input);
        tv_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_input.getText().toString().trim();
                if (content.equals("")){
                    showToast("请输入内容！");
                    return;
                }
                if (i==0){
                    tvUserName.setText(content);
                }else if(i==1){
                    tvZhuanye.setText(content);
                }else if(i==2){
                    tvNianji.setText(content);
                }
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    @Override
    protected int layoutInit() {
        return R.layout.activity_updata_user;
    }

    @Override
    protected void binEvent() {
        userInfo  = (UserInfo) SharePreferencesUtil.readObject(this, ConstantsUtils.CURRENT_USER);
        if (userInfo.getName()==null||userInfo.getName().equals("")){
            tvUserName.setText(userInfo.getPhone());
        }else {
            tvUserName.setText(userInfo.getName());
        }

        tvPhoneNum.setText(userInfo.getPhone());
        if (userInfo.getZhuangye() == null || userInfo.getZhuangye().equals("")) {
            tvZhuanye.setText("未填写");
        } else {
            tvZhuanye.setText(userInfo.getZhuangye());
        }
        if (userInfo.getNiaji() == null || userInfo.getNiaji().equals("")) {
            tvNianji.setText("未填写");
        } else {
            tvNianji.setText(userInfo.getNiaji());
        }


    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UpdataUserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void fillData() {

    }


}
