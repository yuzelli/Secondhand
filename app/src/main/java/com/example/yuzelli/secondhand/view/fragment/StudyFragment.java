package com.example.yuzelli.secondhand.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseFragment;
import com.example.yuzelli.secondhand.bean.Study;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;
import com.example.yuzelli.secondhand.utils.CommonAdapter;
import com.example.yuzelli.secondhand.utils.ViewHolder;
import com.example.yuzelli.secondhand.view.activity.AddStudyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/5/20.
 */

public class StudyFragment extends BaseFragment {
    @BindView(R.id.lv_list)
    ListView lvList;
    @OnClick(R.id.tv_register)
    public void Register(){
        AddStudyActivity.actionStart(context);
    }


    private List<Study> list;
    private Context context;

    @Override
    protected int layoutInit() {
        return R.layout.fragment_study;
    }

    @Override
    protected void bindEvent(View v) {

        context = getActivity();
    }

    @Override
    protected void fillData() {
        list = (List<Study>) SharePreferencesUtil.readObject(getActivity(), ConstantsUtils.STUDY_LIST);
        if (list==null){
            list = new ArrayList<>();
        }
        lvList.setAdapter(new CommonAdapter<Study>(getActivity(),list,R.layout.cell_study) {
            @Override
            public void convert(ViewHolder helper, Study item, int postion) {
                helper.setText(R.id.tv_content,item.getContent());
            }
        });
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}
