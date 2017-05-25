package com.example.yuzelli.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.bean.Goods;

import com.example.yuzelli.secondhand.view.activity.GoodsDetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 51644 on 2017/5/21.
 * 商品列表适配器 ，两列
 */

public class DoubleIGoodAdapter extends BaseAdapter {
    protected Context mContext;
    public ArrayList<Goods> dataList = new ArrayList();

    public DoubleIGoodAdapter(Context mContext, ArrayList<Goods> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        if (dataList.size() % 2 > 0) {
            return dataList.size() / 2 + 1;
        } else {
            return dataList.size() / 2;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        List<Goods> itemList = null;
        if (null == convertView) {

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_goods_list, null);

            holder.good_cell_one = (LinearLayout) convertView.findViewById(R.id.good_item_one);
            holder.good_cell_photo_one = (ImageView) holder.good_cell_one.findViewById(R.id.gooditem_photo);
            holder.good_price_one = (TextView) holder.good_cell_one.findViewById(R.id.good_price);
            holder.good_desc_one = (TextView) holder.good_cell_one.findViewById(R.id.good_desc);

            holder.good_cell_two = (LinearLayout) convertView.findViewById(R.id.good_item_two);
            holder.good_cell_photo_two = (ImageView) holder.good_cell_two.findViewById(R.id.gooditem_photo);
            holder.good_price_two = (TextView) holder.good_cell_two.findViewById(R.id.good_price);
            holder.good_desc_two = (TextView) holder.good_cell_two.findViewById(R.id.good_desc);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int distance = dataList.size() - position * 2;
        int cellCount = distance >= 2 ? 2 : distance;

        itemList = dataList.subList(position * 2, position * 2 + cellCount);

        if (itemList.size() > 0) {
            final Goods goodOne = itemList.get(0);
            ImageLoader.getInstance().displayImage(goodOne.getImg(), holder.good_cell_photo_one);
            holder.good_price_one.setText("￥" + goodOne.getPrice()+"");
            holder.good_desc_one.setText(goodOne.getTitle());

            holder.good_cell_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsDetailActivity.actionStart(mContext,goodOne);
                }
            });
            if (itemList.size() > 1) {
                holder.good_cell_two.setVisibility(View.VISIBLE);
                final Goods goodTwo = itemList.get(1);
                ImageLoader.getInstance().displayImage(goodTwo.getImg(), holder.good_cell_photo_two);
                holder.good_price_two.setText("￥" + goodTwo.getPrice()+"");
                holder.good_desc_two.setText(goodTwo.getTitle());
                holder.good_cell_photo_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsDetailActivity.actionStart(mContext,goodTwo);
                    }
                });
            }
        }
        return convertView;
    }

    private class ViewHolder {
        public LinearLayout good_cell_one;
        public LinearLayout good_cell_two;
        private ImageView good_cell_photo_one;
        private ImageView good_cell_photo_two;
        private TextView good_price_one;
        private TextView good_price_two;
        private TextView good_desc_one;
        private TextView good_desc_two;
    }
}
