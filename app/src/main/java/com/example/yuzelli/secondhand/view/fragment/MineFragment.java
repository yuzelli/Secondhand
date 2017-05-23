package com.example.yuzelli.secondhand.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseFragment;
import com.example.yuzelli.secondhand.bean.UserInfo;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;
import com.example.yuzelli.secondhand.view.activity.AddStudyActivity;
import com.example.yuzelli.secondhand.view.activity.UpdataUserInfoActivity;
import com.example.yuzelli.secondhand.widgets.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/5/19.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.roundImageView)
    RoundImageView roundImageView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.content)
    TextView content;
    @OnClick(R.id.tv_updata)
    public void tvUpdata(){
        UpdataUserInfoActivity.actionStart(getActivity());
    }
    @BindView(R.id.tv_add)
    TextView tvAdd;


    @Override
    protected int layoutInit() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void bindEvent(View v) {

    }

    @Override
    protected void fillData() {
        UserInfo userInfo = (UserInfo) SharePreferencesUtil.readObject(getActivity(), ConstantsUtils.CURRENT_USER);
        if (userInfo.getName() == null || userInfo.getName().equals("")) {
            tvName.setText(userInfo.getPhone());
        } else {
            tvName.setText(userInfo.getName());
        }
        StringBuffer str = new StringBuffer();
        if (userInfo.getZhuangye() == null || userInfo.getZhuangye().equals("")) {
            str.append("专业：" + "未填写" + "\n");
        } else {
            str.append("专业：" + userInfo.getZhuangye() + "\n");
        }
        if (userInfo.getNiaji() == null || userInfo.getNiaji().equals("")) {
            str.append("年纪：" + "未填写");
        } else {
            str.append("年纪：" + userInfo.getNiaji());
        }
        content.setText(str.toString());

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudyActivity.actionStart(getActivity());
            }
        });

    }


}
