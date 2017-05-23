package com.example.yuzelli.secondhand.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.adapter.BannerAdapter;
import com.example.yuzelli.secondhand.adapter.DoubleIGoodAdapter;
import com.example.yuzelli.secondhand.base.BaseFragment;
import com.example.yuzelli.secondhand.bean.Goods;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/5/20.
 */

public class ShareFragment extends BaseFragment implements View.OnTouchListener, ViewPager.OnPageChangeListener, View.OnClickListener {
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.lv_list)
    ListView lvList;
    private ViewPager vp_picture;   //图片轮播
    private TextView tv_vp_title;   //图片轮播的简介
    private LinearLayout ll_Point;

    private BannerAdapter adapter;   //图片轮播adapter
    private ArrayList<ImageView> bannerImageDates;   //图片轮播的图片
    private View bottomView;
    private int currentIndex = 300;   //图片下标
    private long lastTime;           //上一次图片滚动时间
    private Handler handler;
    ArrayList<Goods> goodsList;
    @Override
    protected int layoutInit() {
        return R.layout.fragment_share;
    }

    @Override
    protected void bindEvent(View v) {
        vp_picture = (ViewPager) v.findViewById(R.id.vp_picture);
        tv_vp_title = (TextView) v.findViewById(R.id.tv_vp_title);
        ll_Point = (LinearLayout) v.findViewById(R.id.ll_Point);
        handler = new Handler();


      goodsList = new ArrayList<>();
        goodsList.add(new Goods("http://img3x8.ddimg.cn/54/2/20812428-1_b_0.jpg",12,"《中医学概论》","【普通高等教育“十一五”国家级规划教材】"));
        goodsList.add(new Goods("http://img3x4.ddimg.cn/47/34/22802024-1_b_1.jpg",35,"《军事理论教程》","普通高等学校军事理论教程。"));
        goodsList.add(new Goods("http://img3x3.ddimg.cn/36/11/24073173-1_b_5.jpg",48,"《电子商务概论》"," 经典教材。自2009年出版以来，共出版3个版次、20多个印次，销售10多万册，10多万学子受益。l 版本常新，历久弥新。教材每3年更新一次版本，使得教师讲课内容始终站在电子商务*前沿。"));
        goodsList.add(new Goods("http://img3x5.ddimg.cn/98/6/22709015-1_b_1.jpg",88,"《会计学原理》","（会计系列教材）（“十二五”普通高等教育本科国家级规划教材）。"));
        goodsList.add(new Goods("http://img3x8.ddimg.cn/12/9/20801298-1_b_0.jpg",85,"《C语言程序设计》","现代方法（第2版）(被誉为“近10年来最好的一部C语言著作”) 。"));
        goodsList.add(new Goods("http://img3x0.ddimg.cn/22/4/22588060-1_b_1.jpg",75,"《ERP客户关系管理实务》","（创业教育规划教材·ERP应用实务系列） "));
        goodsList.add(new Goods("http://img3x1.ddimg.cn/22/29/24177901-1_b_1.jpg",25,"《网络营销 》","60W图书4.9折封顶 经管励志分会场。"));
        goodsList.add(new Goods("http://img3x1.ddimg.cn/42/26/20350581-1_b_0.jpg",100, "《系统工程》","系统概念和系统思想是劳动人民在长期社会实践中形成和发展起来的。在人类社会和科学技术发展的历史长河中，系统思想经历了三个主要的历史阶段：从远古时期到15世纪左右是以朴素的辩证逻辑为特点的总体思辨阶段；从16世纪到19世纪是以形式逻辑为特点的机械分解——还原思维阶段；从19世纪末20世纪初到以后是以辩证逻辑为特点的系统思维阶段。"));
        goodsList.add(new Goods("http://img3x1.ddimg.cn/69/32/22871841-1_b_1.jpg",25,"《运筹学》","面向21世纪课程教材（信息管理与信息系统专业教材系列）"));
        goodsList.add(new Goods("http://img3x6.ddimg.cn/55/23/23781736-1_b_2.jpg",100, "《数据库原理及应用》","畅销数据库教材，用一个案例贯穿全教材，每个知识点都通过实例进行讲解，兼顾理论和应用。"));

        DoubleIGoodAdapter adapter = new DoubleIGoodAdapter(getActivity(),  goodsList);
        lvList.setAdapter(adapter);
        updataBanner();

    }
    private void updataBanner() {
        bannerImageDates = new ArrayList<>();
        DisplayImageOptions options = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        for (int i = 0; i < goodsList.size(); i++) {
            ImageView img = new ImageView(getActivity());
            //显示图片的配置
            ImageLoader.getInstance().displayImage(goodsList.get(i).getImg(), img, options);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            bannerImageDates.add(img);
        }
        adapter = new BannerAdapter(getActivity(), bannerImageDates);
        vp_picture.setOnTouchListener(this);
        vp_picture.setAdapter(adapter);
        vp_picture.setCurrentItem(300);
        vp_picture.addOnPageChangeListener(this);
        handler.postDelayed(runnableForBanner, 2000);
        addPoint();
        vp_picture.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                monitorPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    protected void fillData() {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    // 设置轮播时间间隔
    private Runnable runnableForBanner = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - lastTime >= 3000) {
                vp_picture.setCurrentItem(currentIndex);
                currentIndex++;
                lastTime = System.currentTimeMillis();
            }
            handler.postDelayed(runnableForBanner, 3000);
        }
    };
    /**
     * 添加小圆点
     */
    private void addPoint() {
        // 1.根据图片多少，添加多少小圆点
        ll_Point.removeAllViews();

        for (int i = 0; i < goodsList.size(); i++) {
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i < 1) {
                pointParams.setMargins(0, 0, 0, 0);
            } else {
                pointParams.setMargins(10, 0, 0, 0);
            }
            ImageView iv = new ImageView(getActivity());
            iv.setLayoutParams(pointParams);
            iv.setBackgroundResource(R.drawable.point_normal);
            ll_Point.addView(iv);
        }
        ll_Point.getChildAt(0).setBackgroundResource(R.drawable.point_select);
    }

    /**
     * 判断小圆点
     *
     * @param position
     */
    private void monitorPoint(int position) {
        int current = (position - 300) % goodsList.size();
        for (int i = 0; i <goodsList.size(); i++) {
            if (i == current) {
                ll_Point.getChildAt(current).setBackgroundResource(
                        R.drawable.point_select);
            } else {
                ll_Point.getChildAt(i).setBackgroundResource(
                        R.drawable.point_normal);
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        lastTime = System.currentTimeMillis();
        //设置轮播文字改变
        final int index = position % bannerImageDates.size();

        tv_vp_title.setText(goodsList.get(index).getTitle());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
