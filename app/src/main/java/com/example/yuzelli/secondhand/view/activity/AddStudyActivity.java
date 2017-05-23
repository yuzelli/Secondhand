package com.example.yuzelli.secondhand.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseActivity;
import com.example.yuzelli.secondhand.bean.Study;
import com.example.yuzelli.secondhand.bean.UserInfo;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 51644 on 2017/5/20.
 */

public class AddStudyActivity extends BaseActivity {
    @OnClick(R.id.img_back)
    public void back(){
        finish();
    }
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.et_content)
    EditText et_content;
    @OnClick(R.id.tv_cancel)
    public void Cancel(){
        finish();
    }
    @BindView(R.id.tv_add)
    TextView tvAdd;
  private Context context;
    @Override
    protected int layoutInit() {
        return R.layout.activity_add_study;
    }

    @Override
    protected void binEvent() {
        context = this;
        final UserInfo userInfo = (UserInfo) SharePreferencesUtil.readObject(this, ConstantsUtils.CURRENT_USER);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etInput.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                if (title.equals("")){
                    return;
                } if (content.equals("")){
                    return;
                }

                List<Study> list = (List<Study>) SharePreferencesUtil.readObject(context,ConstantsUtils.STUDY_LIST);
                if (list==null){
                    list = new ArrayList<Study>();
                }

                Study s = new Study( list.size(),title,content,userInfo.getPhone());
                list.add(s);
                SharePreferencesUtil.saveObject(context,ConstantsUtils.STUDY_LIST,list);
                finish();
            }
        });

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddStudyActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void fillData() {

    }

    @OnClick(R.id.rl_head)
    public void onViewClicked() {
    }
}
