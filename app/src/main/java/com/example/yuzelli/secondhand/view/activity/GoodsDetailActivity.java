package com.example.yuzelli.secondhand.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.base.BaseActivity;
import com.example.yuzelli.secondhand.bean.Goods;
import com.example.yuzelli.secondhand.bean.Study;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 51644 on 2017/5/21.
 */

public class GoodsDetailActivity extends BaseActivity {
    @BindView(R.id.gooditem_photo)
    ImageView gooditemPhoto;
    @BindView(R.id.good_price)
    TextView goodPrice;
    @BindView(R.id.good_desc)
    TextView goodDesc;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_puy)
    TextView tvPuy;
    Goods goods;

    @Override
    protected int layoutInit() {
        return R.layout.actoivity_good_detail;
    }

    @Override
    protected void binEvent() {
        goods = (Goods) getIntent().getSerializableExtra("goods");
        ImageLoader.getInstance().displayImage(goods.getImg(), gooditemPhoto);
        tvContent.setText(goods.getContent());
        goodPrice.setText("￥" + goods.getPrice() + "");
        goodDesc.setText(goods.getTitle());
        tvPuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogView();
            }
        });

    }

    private void showDialogView() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 构建

        builder.setTitle("订单：");
        builder.setMessage(goods.getTitle()+"将花费您："+goods.getPrice()+"元"+"确定购买么？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("购买成功！");
            }
        });
        builder.setNegativeButton("取消", null);


        builder.show();

    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context, Goods goods) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goods", goods);
        context.startActivity(intent);
    }
}
