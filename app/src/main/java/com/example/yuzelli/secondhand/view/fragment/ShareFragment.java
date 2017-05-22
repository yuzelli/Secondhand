package com.example.yuzelli.secondhand.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.yuzelli.secondhand.R;
import com.example.yuzelli.secondhand.adapter.DoubleIGoodAdapter;
import com.example.yuzelli.secondhand.base.BaseFragment;
import com.example.yuzelli.secondhand.bean.Goods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/5/20.
 */

public class ShareFragment extends BaseFragment {
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected int layoutInit() {
        return R.layout.fragment_share;
    }

    @Override
    protected void bindEvent(View v) {
        ArrayList<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods("http://img3x9.ddimg.cn/68/36/24221309-1_l_4.jpg",10,"《恋情的终结》","马尔克斯、福克纳推崇备至的大师级作家。历史百大英语小说。这本书里有狂热的爱、狂热的恨、狂热的猜疑、狂热的嫉妒、狂热的信仰，有爱情中所有狂热的情感。"));
        goodsList.add(new Goods("http://img3x5.ddimg.cn/31/35/24220975-1_l_6.jpg",10,"《听说你喜欢我》","冷漠傲娇神经外科医生VS深情萌宠小学妹，吉祥夜感动千万人治愈之作！听说你喜欢我？好巧，我也喜欢你。"));
        goodsList.add(new Goods("http://img3x0.ddimg.cn/12/14/24242340-1_l_7.jpg",10,"《半小时漫画中国史》","200万粉丝大号“混子曰”创始人二混子的革命性历史作品。张泉灵鼎力推荐！看半小时漫画，通三千年历史，脉络无比清晰，看完就能倒背。"));
        goodsList.add(new Goods("http://img3x5.ddimg.cn/20/18/25065335-1_l_12.jpg",10,"《你自以为的极限，只是别人的起点》","你自以为的极限，只是别人的起点（当当独家签名版） 预售下单得亲笔签名，更可参与抽奖赢小米手机、Dior润唇蜜等大奖。百万级畅销书作家特立独行的猫重磅新作，写给渴望突破瓶颈、实现快速跨越的你。"));

        goodsList.add(new Goods("http://img3x9.ddimg.cn/3/22/25064229-1_l_2.jpg",10,"《认真地年轻，优雅地老去》","迄今更富诗性与美感的杨绛传记，全彩典藏本。独家收录《钱锺书传》《钱瑗传》，完整展现“我们仨”的动人世界。民国女子大多特立独行，唯独她，不攀高也不怕下跌，用力去爱，用心生活，于从容不迫间，成就百年优雅。"));
        goodsList.add(new Goods("http://img3x4.ddimg.cn/16/31/25063054-1_l_3.jpg",10,"《去旅行》","入选法国教育部向5-8岁儿童推荐书目，被赞“了解世界的旅行百科书”！傅雷翻译奖大师翻译,《博物》《环球科学》主编推荐！人文、地理、历史、政治尽在其中！赠情景模拟硫酸纸卡，开拓孩子眼界的12种有趣方式。"));
        goodsList.add(new Goods("http://img3x3.ddimg.cn/49/20/24236833-1_l_3.jpg",10,"《玛格丽特晚安诗》","《晚安，月亮》《逃家小兔》作者、四次凯迪克奖获得者玛格丽特·怀兹·布朗珍贵遗作在中国首次出版，穿越半个世纪的诗意宝藏——献给孩子的诗与远方，中英双语绘本，英语启蒙读物，附赠典藏音乐CD及音频二维码。"));
        goodsList.add(new Goods("http://img3x5.ddimg.cn/30/15/24244635-1_l_5.jpg",10, "《智能革命》","李彦宏重磅推出新作。“人工智能”正式写入17年政府工作报告，第四次工业革命的号角已经吹响。人工智能和雨果奖获得者刘慈欣联合作序，AR特效互动，与AI界网红小度合影。"));
        DoubleIGoodAdapter adapter = new DoubleIGoodAdapter(getActivity(),  goodsList);
        lvList.setAdapter(adapter);

    }

    @Override
    protected void fillData() {

    }


}
