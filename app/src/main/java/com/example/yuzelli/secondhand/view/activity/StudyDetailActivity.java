package com.example.yuzelli.secondhand.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseActivity;
import com.example.yuzelli.secondhand.bean.Reply;
import com.example.yuzelli.secondhand.bean.Study;
import com.example.yuzelli.secondhand.constants.ConstantsUtils;
import com.example.yuzelli.secondhand.https.SharePreferencesUtil;
import com.example.yuzelli.secondhand.utils.CommonAdapter;
import com.example.yuzelli.secondhand.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资料分享详情
 */
public class StudyDetailActivity extends BaseActivity {


    @BindView(R.id.tv_content)
    TextView tvContent;

    @OnClick(R.id.img_back)
    public void imgBack() {
        finish();
    }

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_reply)
    TextView tvReply;
    @BindView(R.id.lv_reply)
    ListView lvReply;

    private Context context;
    private Study study;

    @Override
    protected int layoutInit() {
        return R.layout.activity_study_detail;
    }

    @Override
    protected void binEvent() {
        context = this;
        study = (Study) getIntent().getSerializableExtra("study");
        tvBuy.setText("求购电话：" + study.getPhone());
        tvTitle.setText(study.getTitle());
        tvContent.setText(study.getContent());
        tvReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyDialog(study.getId());
            }
        });
        updataList();
    }

    private void showMyDialog(final int id) {
        final Dialog dialog = new Dialog(this, R.style.PhotoDialog);
        View view = LayoutInflater.from(StudyDetailActivity.this).inflate(R.layout.dialog, null);
        final EditText content = (EditText) view.findViewById(R.id.et_input);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView button = (TextView) view.findViewById(R.id.tv_add);
        //关键放过，builder提供了setview方法来提供自定义dialog
        dialog.setContentView(view);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String cont = content.getText().toString().trim();
                if (cont.equals("")) {
                    return;
                }
                List<Reply> list = (List<Reply>) SharePreferencesUtil.readObject(context, id + "" + ConstantsUtils.REPLY_LIST);
                if (list == null) {
                    list = new ArrayList<Reply>();
                }
                Reply reply = new Reply();
                reply.setContent(cont);
                reply.setPhone(study.getPhone());
                list.add(reply);
                SharePreferencesUtil.saveObject(context, id + "" + ConstantsUtils.REPLY_LIST, list);
                updataList();
                dialog.dismiss();
            }
        });
        dialog.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void updataList() {
        List<Reply> list = (List<Reply>) SharePreferencesUtil.readObject(context, study.getId() + "" + ConstantsUtils.REPLY_LIST);
        if (list == null) {
            list = new ArrayList<>();
        }
        lvReply.setAdapter(new CommonAdapter<Reply>(context, list, R.layout.cell_study) {
            @Override
            public void convert(ViewHolder helper, Reply item, int postion) {
                helper.setText(R.id.tv_content, item.getContent());
            }
        });
    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context, Study study) {
        Intent intent = new Intent(context, StudyDetailActivity.class);
        intent.putExtra("study", study);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
