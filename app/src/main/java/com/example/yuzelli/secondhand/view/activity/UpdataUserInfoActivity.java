package com.example.yuzelli.secondhand.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseActivity;
import com.example.yuzelli.secondhand.bean.UserInfo;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;

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
    @OnClick({R.id.rl_phoneNum, R.id.rl_userName, R.id.rl_zhuanye, R.id.rl_nianji})
    public void viewClick(View v) {
         switch (v.getId()){
             case R.id.rl_phoneNum:
                 showUpDialog(0);
                 break;
             case R.id.rl_userName:
                 break;
             case R.id.rl_zhuanye:
                 break;
             case R.id.rl_nianji:
                 break;
             default:
                 break;

         }
    }

    private void showUpDialog(int i) {
        final Dialog dialog = new Dialog(this,R.style.PhotoDialog);
        final View view = LayoutInflater.from(UpdataUserInfoActivity.this).inflate(R.layout.personal_head_select_diallog,null);
        dialog.setContentView(view);
        TextView tv_Cancel =  (TextView)view.findViewById(R.id.tv_cancel);
        tv_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    protected void fillData() {

    }


}
